# Object Detection

Object detection answers two questions at once: **what** objects are in an
image, and **where** they are. Unlike classification, which assigns a
single label to an image, detection produces a variable number of
bounding boxes with associated class probabilities.

This page focuses on the architectural split between **two-stage** and
**one-stage** detectors, with YOLO as the canonical example of the
one-stage approach.

---

## The Detection Task

A detection model outputs a set of predictions, each containing:

- A **bounding box** `(x, y, width, height)`.
- A **class label**.
- A **confidence score**.

The number of objects is not fixed: an image may contain zero, one, or
hundreds of objects. This makes detection harder than classification
because the output structure is variable.

### Example output

```text
Person    0.97  [x=120, y=85, w=90, h=220]
Bicycle   0.91  [x=110, y=260, w=120, h=80]
Car       0.88  [x=300, y=200, w=150, h=100]
```

---

## Two-Stage Detectors

Two-stage detectors separate localization from classification:

1. **Region proposal.** A first stage scans the image and proposes a set
   of candidate object locations.
2. **Classification and refinement.** A second stage classifies each
   proposal and refines its bounding box coordinates.

### R-CNN lineage

| Model | Year | Key idea |
|-------|------|----------|
| R-CNN | 2014 | Run CNN on ~2,000 selective-search region proposals |
| Fast R-CNN | 2015 | Share convolutional features across proposals for speed |
| Faster R-CNN | 2015 | Replace selective search with a learned Region Proposal Network (RPN) |

Two-stage detectors generally achieve higher **mean Average Precision
(mAP)**, especially on datasets with many small or overlapping objects.
Their weakness is speed: the two-pass design limits real-time use.

---

## One-Stage Detectors

One-stage detectors predict boxes and class probabilities in a single
forward pass. They trade a small amount of accuracy for a large gain in
speed, making them the default choice for real-time video and edge
devices.

### YOLO: You Only Look Once

**YOLO**, introduced by Joseph Redmon et al. in 2016, reframed detection
as a single **regression problem**. The input image is divided into a
grid; each grid cell predicts a fixed number of bounding boxes and class
probabilities. The entire pipeline runs in one network evaluation.

Key design choices:

- **Unified loss.** A single loss function combines localization error,
  classification error, and confidence error.
- **Global context.** Because the network sees the whole image, it learns
  contextual relationships (e.g., a person is more likely to appear above
  a bicycle).
- **Speed first.** Early versions ran at 45–155 FPS, far ahead of
  contemporary two-stage methods.

### YOLO versions at a glance

| Version | Year | Notable change |
|---------|------|----------------|
| YOLO v1 | 2016 | Introduced unified real-time detection |
| YOLO v2 / 9000 | 2016 | Batch normalization, anchor boxes, multi-scale training |
| YOLO v3 | 2018 | Feature pyramid (FPN-style) for three scales; Darknet-53 backbone |
| YOLO v4 | 2020 | Bag of freebies and specials; CSPDarknet53 |
| YOLO v5+ | 2020+ | Ultralytics ecosystem; PyTorch-native training and deployment |
| YOLOv8–v10 | 2023+ | Anchor-free designs, decoupled heads, speed/accuracy improvements |

!!! note "Version lineage"
    After YOLO v3, Joseph Redmon stepped away from computer vision
    research. Later versions were developed by different teams
    (Alexey Bochkovskiy for v4, Ultralytics for v5+, etc.). The name
    "YOLO" became a family of architectures rather than a single
    research line.

### Single Shot MultiBox Detector (SSD)

SSD is another influential one-stage detector. It uses multiple feature
maps at different resolutions to detect objects of various sizes, and
relies heavily on **default boxes** (anchor boxes) at different aspect
ratios. SSD demonstrated that one-stage detectors could be both fast and
accurate enough for many applications.

---

## How YOLO Works in Detail

### Grid and anchor boxes

The image is divided into an `S × S` grid. Each cell predicts:

- `B` bounding boxes, each with `(x, y, w, h, confidence)`.
- `C` class probabilities conditioned on the cell containing an object.

At inference, predictions are filtered by confidence and then by
**Non-Maximum Suppression (NMS)** to remove duplicate boxes around the
same object.

### Loss function

The original YOLO loss has three parts:

```text
Loss = λ_coord · localization_error
     + λ_obj · confidence_error
     + classification_error
```

- **Localization error** penalizes incorrect box coordinates and sizes.
- **Confidence error** penalizes objectness scores.
- **Classification error** penalizes wrong class predictions.

Because most grid cells do not contain objects, the loss uses weights
(`λ_coord`, `λ_obj`) to balance the contribution of positive and negative
examples.

### Non-Maximum Suppression (NMS)

A single object may trigger multiple overlapping predictions. NMS keeps
the highest-confidence box and discards nearby boxes whose **IoU**
(Intersection over Union) exceeds a threshold, typically 0.5.

```text
For each class:
    Sort predictions by confidence.
    Take the top prediction.
    Remove all predictions with IoU(top, pred) > threshold.
    Repeat until no predictions remain.
```

Modern variants use softer NMS, DIoU-NMS, or learnable NMS to improve
precision, especially for crowded scenes.

---

## One-Stage vs Two-Stage Trade-Off

| Aspect | Two-stage (Faster R-CNN) | One-stage (YOLO, SSD) |
|--------|--------------------------|-----------------------|
| Stages | Proposal + classification | Single network pass |
| Speed | Slow to moderate | Fast to very fast |
| Small objects | Better | Historically weaker; improved with FPN and multi-scale heads |
| Overlap / crowds | Better | Can struggle without NMS tuning |
| Interpretability | Easier to inspect proposals | End-to-end; harder to debug |
| Typical use | Offline analysis, high-accuracy benchmarks | Real-time video, robotics, edge |

The gap has narrowed over time. Modern one-stage detectors use feature
pyramids, anchor-free designs, and focal loss to match two-stage accuracy
on many benchmarks while retaining speed.

---

## Evaluation Metrics

### Intersection over Union (IoU)

```text
IoU = area(predicted_box ∩ ground_truth_box)
    / area(predicted_box ∪ ground_truth_box)
```

A prediction is a **true positive** if IoU ≥ threshold (usually 0.5).

### Precision and Recall

- **Precision** = true positives / total predictions.
- **Recall** = true positives / total ground-truth objects.

### mean Average Precision (mAP)

Average Precision (AP) is the area under the precision-recall curve for a
single class. mAP averages AP across all classes. COCO-style mAP also
averages over multiple IoU thresholds (0.50:0.05:0.95), rewarding more
precise localization.

---

## Production Considerations

**Input resolution.** Higher resolution improves small-object detection
but increases compute. A common engineering decision is to downsize
inputs to match the latency budget.

**Anchor design.** YOLO v2–v5 use anchor boxes tuned to the dataset.
Poorly chosen anchors hurt recall for unusual aspect ratios.

**Quantization.** Converting weights from FP32 to INT8 or FP16 roughly
doubles throughput and halves memory, but can reduce mAP by a few points.
Calibration on representative data is essential.

**Deployment targets.** YOLO models are exported to ONNX, TensorRT,
OpenVINO, Core ML, and Edge TPU formats. Each target has different
operator support and optimization passes.

**Drift and monitoring.** Camera changes, lighting, and seasonal
variation degrade model performance. Track per-class precision/recall in
production and trigger retraining when metrics drift.

---

## Connections

- **Builds on:** [Convolutional Neural Networks](./index.md#convolutional-neural-networks)
- **Led to:** Real-time perception stacks in autonomous vehicles, robotics, and surveillance
- **Paper:** [You Only Look Once: Unified, Real-Time Object Detection](../../works/papers/redmon-2016-yolo.md) (2016)
- **Author:** [Joseph Redmon](../../authors/joseph-redmon.md)
- **Related topic:** [Computer Vision](./index.md)
