// Testing in TypeScript
// ======================
// Jest, assertions, mocking, async tests

// ── Basic test ───────────────────────────────────

describe("Basic tests", () => {
    test("addition", () => {
        expect(5 + 3).toBe(8);
    });

    test("string operations", () => {
        const s = "hello";
        expect(s.length).toBe(5);
        expect(s.toUpperCase()).toBe("HELLO");
    });
});

// ── Object testing ───────────────────────────────────

describe("Object tests", () => {
    test("object equality", () => {
        const obj1 = { name: "Alice", age: 30 };
        const obj2 = { name: "Alice", age: 30 };
        expect(obj1).toEqual(obj2);
    });

    test("array operations", () => {
        const nums = [1, 2, 3];
        expect(nums.reduce((a, b) => a + b, 0)).toBe(6);
        expect(nums).toHaveLength(3);
    });
});

// ── Exception testing ──────────────────────────────

describe("Exception tests", () => {
    test("division by zero", () => {
        expect(() => divide(10, 0)).toThrow();
    });

    test("no exception", () => {
        expect(divide(10, 2)).toBe(5);
    });
});

function divide(a: number, b: number): number {
    if (b === 0) {
        throw new Error("division by zero");
    }
    return a / b;
}

// ── Async tests ─────────────────────────────────────

describe("Async tests", () => {
    test("async function", async () => {
        const result = await asyncAdd(5, 3);
        expect(result).toBe(8);
    });

    test("async error", async () => {
        await expect(asyncFail()).rejects.toThrow("error");
    });
});

async function asyncAdd(a: number, b: number): Promise<number> {
    return new Promise(resolve => {
        setTimeout(() => resolve(a + b), 100);
    });
}

async function asyncFail(): Promise<never> {
    throw new Error("error");
}

// ── Mocking example ─────────────────────────────

describe("Mocking", () => {
    test("mock function", () => {
        const mockFn = jest.fn(() => 42);
        mockFn();
        expect(mockFn).toHaveBeenCalled();
        expect(mockFn).toHaveReturnedWith(42);
    });

    test("spied object", () => {
        const calculator = {
            add: jest.fn((a: number, b: number) => a + b)
        };
        calculator.add(5, 3);
        expect(calculator.add).toHaveBeenCalledWith(5, 3);
    });
});

// ── Parameterized tests ─────────────────────────────

describe.each([
    [1, 1],
    [2, 4],
    [3, 9],
    [-5, 25],
])("Square of %d", (input: number, expected: number) => {
    test(`returns ${expected}`, () => {
        expect(input * input).toBe(expected);
    });
});

// ── Setup/teardown ──────────────────────────────

describe("Lifecycle", () => {
    let counter = 0;

    beforeEach(() => {
        counter = 0;
    });

    test("increment counter", () => {
        counter++;
        expect(counter).toBe(1);
    });

    afterEach(() => {
        counter = 0;
    });
});

// ── Snapshot testing ───────────────────────────────

describe("Snapshot", () => {
    test("snapshot object", () => {
        const data = { id: 1, name: "Test" };
        expect(data).toMatchSnapshot();
    });
});

// ── Summary ─────────────────────────────────────

describe("Summary", () => {
    test("TypeScript testing features", () => {
        expect(true).toBe(true);
        expect(false).not.toBe(true);
    });
});

// ── Running tests ───────────────────────────────────
// Run with: npm test, jest, or ts-node node_modules/jest/bin/jest
