;;; Data Structures in Lisp
;;; =============================
;;; Demonstrates lists, arrays (vectors), alists (association lists),
;;; hash tables, and common operations.

;; ── Lists (linked) ─────────────────────────────────

(format t "--- Lists (linked) ---")

;; List literal
(setq fruits '(apple banana cherry))
(format t "fruits = ~A~%" fruits)

;; car/cdr (head/tail)
(format t "car (first) = ~A~%" (car fruits))
(format t "cdr (rest) = ~A~%" (cdr fruits))

;; cons (prepend)
(setq fruits2 (cons 'date fruits))
(format t "After cons 'date': ~A~%" fruits2)

;; append (inefficient, uses nconc for efficiency)
(setq fruits3 (append fruits '(date)))
(format t "After append 'date': ~A~%" fruits3)

;; list operations
(format t "length = ~D~%" (length fruits))
(format t "nth 2 = ~A~%" (nth 2 fruits))
(format t "reverse = ~A~%" (reverse fruits))
(format t "member 'cherry'? ~:[~t]~%" (member 'cherry' fruits))

;; List comprehension (loop)
(format t "~%nevens (1-10): ")
(loop for x from 1 to 10
  when (zerop (% x 2))
    (format t "~D " x))


;; ── Vectors (arrays) ─────────────────────────────

(format t "~%n--- Vectors (arrays) ---")

;; Vector literal (1-dimensional)
(setq numbers #(10 20 30 40 50))
(format t "numbers = ~A~%" numbers)

;; Indexing (O(1) for vectors!)
(format t "numbers[0] = ~A~%" (aref numbers 0))   ; 10
(format t "numbers[4] = ~A~%" (aref numbers 4))   ; 50

;; Slicing
(format t "numbers[1:3] = ~A~%" (subseq numbers 1 3))

;; Length
(format t "length = ~D~%" (length numbers))

;; Vector operations
(format t "vectorp? ~:[~t]~%" (vectorp numbers))  ; Check if vector

;; make-array (create vector from list)
(setq array (make-array 5 0))
(format t "array = ~A~%" array)
(aset array 2 42)
(format t "array[2] after set: ~A~%" (aref array 2))

;; fill-pointer (copy vector)
(setq copied (copy-seq numbers))
(format t "copied vector = ~A~%" copied)


;; ── Alists (association lists) ─────────────────────

(format t "~%n--- Alists (association lists) ---")

;; Alist literal (key . value pairs)
(setq person '((name . "Ada") (age . 36) (city . "London")))
(format t "person = ~A~%" person)

;; Get value from alist
(defun get-from-alist (alist key)
  (cdr (assoc key alist)))

(format t "name = ~A~%" (get-from-alist person 'name))
(format t "age = ~A~%" (get-from-alist person 'age))
(format t "unknown = ~A~%" (get-from-alist person 'unknown'))

;; Add to alist (uses push with new pair)
(setq person2 (cons (cons 'email "ada@example.com") person))
(format t "After add email: ~A~%" person2)

;; Remove from alist
(setq person3 (delete (city person2)))
(format t "After remove city: ~A~%" person3)

;; Check key existence
(defun alist-has-key-p (alist key)
  (not (null (assoc key alist))))

(format t "has 'name'? ~:[~t]~%" (alist-has-key-p person 'name))
(format t "has 'email'? ~:[~t]~%" (alist-has-key-p person 'email'))

;; plist (modern association lists)
(format t "~%n--- Property lists (plists) ---")

(setq person-plist '(name "Ada" age 36 city "London"))
(format t "person-plist = ~A~%" person-plist)

;; getf (get from plist)
(format t "name = ~A~%" (getf 'name person-plist))
(format t "age = ~D~%" (getf 'age person-plist))
(format t "unknown = ~A~%" (getf 'unknown person-plist))


;; ── Hash Tables ───────────────────────────────────

(format t "~%n--- Hash Tables ---")

;; make-hash-table (create empty hash table)
(setq hash-table (make-hash-table :test 'equal))
(format t "hash-table = ~A~%" hash-table)

;; puthash (add key-value pair)
(puthash 'name "Ada" hash-table)
(puthash 'age 36 hash-table)
(puthash 'city "London" hash-table)

;; gethash (lookup value)
(format t "name = ~A~%" (gethash 'name hash-table))
(format t "age = ~A~%" (gethash 'age hash-table))
(format t "unknown = ~A~%" (gethash 'unknown hash-table))

;; remhash (remove key)
(remhash 'city hash-table)
(format t "After remove city: ~A~%" hash-table)

;; clrhash (clear all)
(setq hash-table2 (make-hash-table :test 'equal))
(puthash 'x 10 hash-table2)
(puthash 'y 20 hash-table2)
(format t "hash-table2 before clear: ~A~%" hash-table2)
(clrhash hash-table2)
(format t "hash-table2 after clear: ~A~%" hash-table2)


;; ── Array as sequence ─────────────────────────────

(format t "~%n--- Array as sequence ---")

;; array-as-list (treat array as list)
(defun array-elements (arr)
  (let ((result '()))
    (dotimes (i (length arr))
      (push (aref arr i) result))
    (nreverse result)))

(setq arr #(1 2 3 4 5))
(format t "array as list: ~A~%" (array-elements arr))

;; mapcar over array
(format t "mapcar * 2 on array: ~A~%"
        (mapcar (lambda (x) (* x x)) arr))


;; ── Stacks (using lists) ─────────────────────

(format t "~%n--- Stacks (LIFO) ---")

(setq stack '())

(defun stack-push (stack item)
  (push item stack))

(defun stack-pop (stack)
  (pop stack))

(stack-push stack 1)
(stack-push stack 2)
(stack-push stack 3)

(format t "stack: ~A~%" stack)

(format t "popped: ~A~%" (stack-pop stack))
(format t "stack now: ~A~%" stack)


;; ── Queues (using lists) ─────────────────────

(format t "~%n--- Queues (FIFO) ---")

(setq queue '())

(defun queue-enqueue (queue item)
  (nconc queue (list item)))

(defun queue-dequeue (queue)
  (let ((item (car queue)))
    (setq queue (cdr queue))
    item))

(queue-enqueue queue 'first)
(queue-enqueue queue 'second)
(queue-enqueue queue 'third)

(format t "queue: ~A~%" queue)

(format t "dequed: ~A~%" (queue-dequeue queue))
(format t "queue now: ~A~%" queue)


;; ── Sets (using lists with uniqueness) ───────────

(format t "~%n--- Sets ---")

(defun list-to-set (list)
  "Remove duplicates while preserving order."
  (let ((result '()))
    (dolist (item list)
      (unless (member item result)
        (push item result)))
    (nreverse result)))

(setq tags '(python typing python data))
(format t "with duplicates: ~A~%" tags)

(format t "as set (unique): ~A~%" (list-to-set tags))

;; Set operations
(defun set-union (set1 set2)
  (list-to-set (append set1 set2)))

(defun set-intersection (set1 set2)
  (list-to-set
    (dolist (item set1)
      (when (member item set2)
        (push item result)))))

(defun set-difference (set1 set2)
  (list-to-set
    (dolist (item set1)
      (unless (member item set2)
        (push item result))))

(let ((set-a '(1 2 3 4 5))
      (set-b '(4 5 6 7 8)))
  (format t "union: ~A~%" (set-union set-a set-b))
  (format t "intersection: ~A~%" (set-intersection set-a set-b))
  (format t "difference a-b: ~A~%" (set-difference set-a set-b)))


;; ── Binary Data (numbers as bits) ─────────────

(format t "~%n--- Binary Data ---")

;; bit operations
(format t "bitwise AND: 5 & 3 = ~D~%" (logand 5 3))
(format t "bitwise OR: 5 | 3 = ~D~%" (logior 5 3))
(format t "bitwise XOR: 5 ^ 3 = ~D~%" (logxor 5 3))
(format t "bitwise NOT: ~5 = ~D~%" (lognot 5))

;; bit shift
(format t "left shift: 1 << 3 = ~D~%" (ash 1 3))
(format t "right shift: 16 >> 2 = ~D~%" (ash 16 2))


;; ── Summary ───────────────────────────────────────────

(format t "~%n--- Summary ---")
(format t "Lisp data structures:")
(format t "  - List: linked, car/cdr for head/tail")
(format t "  - Vector: 1D array, O(1) indexing")
(format t "  - Alist: key-value pairs (association lists)")
(format t "  - Hash table: make-hash-table, gethash, puthash")
(format t "  - Property list: modern association lists (getf)")
(format t "  - Arrays: make-array, aset, aref")
(format t "  - Homoiconicity: code = data (lists)")
(format t "  - Cons: prepend (append is O(n))")
(format t "  - Nconc: efficient append")
(format t "  - Dynamic evaluation: eval for code-as-data")
