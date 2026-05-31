# Attention Is All You Need

| | |
|---|---|
| **Authors** | Ashish Vaswani, Noam Shazeer, Niki Parmar, Jakob Uszkoreit, Llion Jones, Aidan N. Gomez, Łukasz Kaiser, Illia Polosukhin |
| **Year** | 2017 |
| **Publication** | *Advances in Neural Information Processing Systems (NeurIPS)* |
| **Topic(s)** | LLMs, transformer architecture, attention mechanism |
| **PDF** | [arXiv](https://arxiv.org/abs/1706.03762) |

## Summary

The paper that introduced the **transformer** architecture — the neural
network design that underpins virtually every modern large language model.
The transformer replaced recurrence and convolution with **self-attention**,
allowing the model to attend to all positions in a sequence simultaneously
and enabling parallel training at scale.

The title is literal: the authors demonstrated that attention mechanisms
alone, without recurrence or convolution, achieve state-of-the-art results
on machine translation while training significantly faster.

## Key Ideas

### 1. Self-Attention Mechanism

The core innovation: instead of processing tokens sequentially (RNNs) or
with local windows (CNNs), the transformer computes relationships between
all pairs of tokens in parallel using scaled dot-product attention:

```
Attention(Q, K, V) = softmax(QK^T / √d_k) · V
```

Every token can directly attend to every other token, regardless of
distance in the sequence.

### 2. Multi-Head Attention

Instead of a single attention computation, the model uses multiple
"heads" in parallel — each learning different types of relationships
(syntactic, semantic, positional). The outputs are concatenated and
projected.

### 3. Positional Encoding

Since attention is permutation-invariant, the model adds sinusoidal
positional encodings to input embeddings, allowing it to distinguish
"The cat sat" from "sat cat The".

### 4. Encoder-Decoder Architecture

The original transformer used an encoder-decoder structure for machine
translation. Modern LLMs (GPT) use only the decoder; BERT uses only
the encoder.

## Impact

This paper is the foundation of the modern LLM era:

- **GPT series** (2018–2024) — decoder-only transformers
- **BERT** (2018) — bidirectional encoder transformers
- **T5, LLaMA, Claude, Gemini** — all transformer-based
- Every major LLM API (OpenAI, Anthropic, Google, Meta) uses variants
  of this architecture

The transformer enabled the scaling laws that showed predictable
improvement with model size, data, and compute — the empirical
foundation of the LLM industry.

## Connections

- **Led to:** GPT (Radford 2018), BERT (Devlin 2018), GPT-3 (Brown 2020),
  [Scaling Laws (Kaplan 2020)](./kaplan-2020-scaling-laws.md)
- **Builds on:** Bahdanau attention (2014), LSTM/GRU recurrent networks
- **Authors:** [Ashish Vaswani](../../authors/ashish-vaswani.md)
- **Related topic:** [Transformer Architecture](../../topics/llm/transformer.md) ·
  [Large Language Models](../../topics/llm/index.md)
