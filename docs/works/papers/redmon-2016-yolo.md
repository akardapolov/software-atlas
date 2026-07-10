# You Only Look Once: Unified, Real-Time Object Detection

| | |
|---|---|
| **Authors** | Joseph Redmon, Santosh Divvala, Ross Girshick, Ali Farhadi |
| **Year** | 2016 |
| **Publication** | *Proceedings of the IEEE Conference on Computer Vision and Pattern Recognition (CVPR)* |
| **Topic(s)** | Computer vision, object detection, real-time deep learning |
| **PDF** | [arXiv](https://arxiv.org/abs/1506.02640) |

## Summary

Before YOLO, object detection was dominated by **two-stage pipelines**:
first generate region proposals, then classify each proposal with a
convolutional network. These systems were accurate but slow, often
operating at tens of seconds per image or a few frames per second at
best. Real-time detection — a requirement for robotics, autonomous
driving, and video analytics — remained out of reach for high-quality
models.

Redmon et al. reframed detection as a single **regression problem**. A
single neural network takes an image, divides it into a grid, and
predicts bounding boxes and class probabilities for each grid cell in
one forward pass. This unified approach, named **You Only Look Once
(YOLO)**, achieved competitive accuracy while running at **45 frames per
second** (and a fast variant at 155 FPS), making real-time object
detection practical.

## Key Ideas

### 1. Unified Detection as Regression

YOLO predicts detections directly from full images with a single network
evaluation. The output is a tensor of shape `S × S × (B · 5 + C)`:

- `S × S` grid cells.
- `B` bounding boxes per cell, each with `(x, y, width, height, confidence)`.
- `C` class probabilities.

This design treats detection as a regression problem rather than a
classification-over-proposals problem.

### 2. End-to-End Training

The entire model is trained end-to-end using a single loss function:

```text
Loss = λ_coord · Σ localization_error
     + λ_obj · Σ confidence_error
     + Σ classification_error
```

The loss simultaneously optimizes box coordinates, objectness scores,
and class probabilities. Because most grid cells do not contain objects,
YOLO down-weights confidence loss for empty cells to avoid the model
predicting "no object" for everything.

### 3. Global Reasoning

Because the network sees the entire image during training and inference,
it learns contextual relationships. A model trained on natural images
learns, for example, that people tend to appear near bicycles and that
cars appear on roads. Two-stage detectors, which classify isolated
proposals, have less direct access to this global context.

### 4. Real-Time Speed

The base YOLO model ran at **45 FPS** on a Titan X GPU, with a smaller
**Fast YOLO** variant reaching **155 FPS** at slightly lower mAP. This
was orders of magnitude faster than R-CNN and Faster R-CNN at the time
and opened detection to latency-sensitive applications.

## Historical Context

In 2014, **R-CNN** showed that deep convolutional networks could be
applied to object detection by classifying thousands of region proposals.
**Fast R-CNN** (2015) and **Faster R-CNN** (2015) improved speed by
sharing convolutions and learning proposals, but the two-stage design
still limited throughput.

The computer vision community was also racing to apply CNNs to video,
robotics, and autonomous vehicles — all domains where per-frame latency
matters. YOLO arrived at a moment when accuracy had improved enough that
a real-time, unified design became viable.

## Impact and Legacy

- **Real-time detection became mainstream.** YOLO demonstrated that a
  single network could run fast enough for video without abandoning
  accuracy entirely.
- **One-stage detector family.** YOLO inspired SSD, RetinaNet, EfficientDet,
  and later YOLO versions, creating a full family of fast detectors.
- **Engineering adoption.** YOLO became the default choice for demos,
  hackathons, and production systems that needed fast bounding-box output.
- **Anchor boxes and later architectures.** YOLO v2 introduced anchor
  boxes and batch normalization; subsequent versions added feature pyramids,
  anchor-free heads, and decoupled classification/localization losses.
- **Ethical pause.** Joseph Redmon, the lead author, stopped computer
  vision research after YOLO v3, citing concerns over military and
  surveillance uses of the technology.

## Connections

- **Builds on:** [R-CNN / Fast R-CNN / Faster R-CNN](https://arxiv.org/abs/1311.2524) — two-stage detection lineage
- **Led to:** [SSD](https://arxiv.org/abs/1512.02325), RetinaNet, YOLOv2–v10, and modern real-time detectors
- **Author:** [Joseph Redmon](../../authors/joseph-redmon.md)
- **Related topic:** [Computer Vision](../../topics/computer-vision/index.md) ·
  [Object Detection](../../topics/computer-vision/object-detection.md)
