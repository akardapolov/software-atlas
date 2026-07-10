# Joseph Redmon

| | |
|---|---|
| **Born** | 1984 |
| **Fields** | Computer vision, machine learning, real-time systems |
| **Known for** | YOLO (You Only Look Once); one-stage object detection |

## Biography

Joseph Redmon is an American computer scientist best known as the creator
of **YOLO (You Only Look Once)**, the influential family of real-time
object-detection systems. He conducted his doctoral work at the
University of Washington, where he researched efficient deep learning
for computer vision under Ali Farhadi.

Redmon became a prominent public figure in the vision community not only
for YOLO's technical impact but also for his accessible presentations
and the widespread adoption of his work. In 2020, he made the unusual
decision to **stop conducting computer vision research**, citing ethical
concerns over the use of object-detection technology in military and
surveillance systems. He left a public note explaining that he did not
want his work to contribute to these applications.

## Key Contributions

### YOLO v1 (2016)

Redmon, together with Santosh Divvala, Ross Girshick, and Ali Farhadi,
published "You Only Look Once: Unified, Real-Time Object Detection."
YOLO reframed object detection as a single regression problem, predicting
bounding boxes and class probabilities in one network pass. The base
model ran at 45 FPS, with a fast variant at 155 FPS — far ahead of
two-stage competitors.

### YOLO v2 / YOLO9000 (2016)

Redmon and Farhadi introduced batch normalization, high-resolution
classifiers, anchor boxes, and multi-scale training. YOLO9000 could
detect over 9,000 object categories by jointly training on detection and
classification data.

### YOLO v3 (2018)

Redmon and Farhadi released YOLO v3 with a Darknet-53 backbone and
predictions at three scales using a feature-pyramid-like structure. It
improved small-object detection while remaining fast.

### Ethical stance on computer vision

In 2020, Redmon publicly withdrew from computer vision research because
of concerns about dual-use and harmful applications. His decision became
a notable example of an engineer choosing to limit the reach of their own
inventions on ethical grounds.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2016 | You Only Look Once: Unified, Real-Time Object Detection | Paper | [→](../works/papers/redmon-2016-yolo.md) |
| 2016 | YOLO9000: Better, Faster, Stronger | Paper | — |
| 2018 | YOLOv3: An Incremental Improvement | Paper | — |

## Influence

### Influenced by

- **Ross Girshick** — R-CNN and Faster R-CNN established the two-stage
detection paradigm that YOLO reacted against and improved upon in speed.
- **Ali Farhadi** — PhD advisor and co-author on YOLO v2 and v3.

### Influenced

- **Alexey Bochkovskiy et al.** — YOLOv4 (2020), developed after Redmon
  stepped away from the project.
- **Ultralytics** — YOLOv5 and later versions, which turned YOLO into a
  broad PyTorch-based ecosystem.
- **Real-time vision community** — YOLO became a default architecture for
  video analytics, robotics, and edge devices.

## Quotes

> "I stopped doing CV research because I saw the impact my work was
> having. I loved the work but the military applications and privacy
> concerns eventually became impossible to ignore."

## Further Reading

- [Personal site and CV](https://pjreddie.com/)
- [YOLO project page](https://pjreddie.com/darknet/yolo/)

## Related Pages

- [Computer Vision](../topics/computer-vision/index.md)
- [Object Detection](../topics/computer-vision/object-detection.md)
- [You Only Look Once (2016)](../works/papers/redmon-2016-yolo.md)
