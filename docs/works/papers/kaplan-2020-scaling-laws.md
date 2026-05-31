# Scaling Laws for Neural Language Models

| | |
|---|---|
| **Authors** | Jared Kaplan, Sam McCandlish, Tom Henighan, Tom B. Brown, Benjamin Chess, Rewon Child, Scott Gray, Alec Radford, Jeffrey Wu, Dario Amodei |
| **Year** | 2020 |
| **Publication** | *arXiv preprint* |
| **Topic(s)** | LLMs, scaling, compute-optimal training |
| **PDF** | [arXiv](https://arxiv.org/abs/2001.08361) |

## Summary

This paper demonstrated that language model performance follows
**predictable power-law relationships** as a function of three variables:
model size (parameters), dataset size (tokens), and compute budget
(FLOPs). These **scaling laws** made LLM development an engineering
discipline: given a budget, you can predict the optimal allocation
between parameters and training data.

The practical implication: bigger models, trained on more data, with
more compute, produce reliably better results — and you can predict
*how much* better before spending the money.

## Key Ideas

### 1. Power-Law Scaling

Loss (a proxy for model quality) scales as a power law with each of
the three factors independently:

```
L(N) ∝ N^(-α)        where N = model parameters
L(D) ∝ D^(-β)        where D = training tokens
L(C) ∝ C^(-γ)        where C = compute (FLOPs)
```

The exponents α, β, γ are empirically determined and hold across
orders of magnitude.

### 2. Compute-Optimal Training

Given a fixed compute budget, there is an optimal trade-off between
model size and training data. The original paper suggested that models
should grow larger as compute increases. The later **Chinchilla paper**
(Hoffmann et al., 2022) revised this, showing that many models were
undertrained — data should scale roughly equally with parameters.

### 3. Predictability

Perhaps the most important result: you can train small models and
extrapolate to predict the performance of large models. This enables
experimental iteration without billion-dollar training runs.

## Impact

- Justified the investment in ever-larger models (GPT-3 at 175B,
  GPT-4 at ~1T+ parameters)
- Enabled compute-optimal training decisions
- Led to the "bigger is better" era of LLM development
- Chinchilla (2022) revised the laws, shifting focus toward more data

## Connections

- **Builds on:** [Attention Is All You Need (Vaswani 2017)](./vaswani-2017-attention.md)
- **Led to:** GPT-3 (Brown 2020), Chinchilla (Hoffmann 2022),
  compute-optimal training practices
- **Author:** [Jared Kaplan](../../authors/jared-kaplan.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Transformer Architecture](../../topics/llm/transformer.md)
