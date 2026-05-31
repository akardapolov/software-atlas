# Training language models to follow instructions with human feedback

| | |
|---|---|
| **Authors** | Long Ouyang, Jeff Wu, Xu Jiang, Diogo Almeida, Carroll Wainwright, Pamela Mishkin, Chong Zhang, Sandhini Agarwal, Katarina Slama, Alex Ray, John Schulman, Jacob Hilton, Fraser Kelton, Luke Miller, Maddie Simens, Amanda Askell, Peter Welinder, Paul Christiano, Jan Leike, Ryan Lowe |
| **Year** | 2022 |
| **Publication** | *Advances in Neural Information Processing Systems (NeurIPS)* |
| **Topic(s)** | LLMs, alignment, RLHF, instruction tuning |
| **PDF** | [arXiv](https://arxiv.org/abs/2203.02155) |

## Summary

The InstructGPT paper introduced the **RLHF** (Reinforcement Learning
from Human Feedback) pipeline that turned GPT-3 from a text predictor
into a helpful, harmless assistant. This three-step process became the
template for aligning virtually every major LLM, including ChatGPT,
Claude, and Gemini.

The key result: a 1.3B parameter InstructGPT model was preferred by
human evaluators over the 175B parameter base GPT-3 — demonstrating
that alignment training matters more than raw scale for user experience.

## Key Ideas

### 1. Supervised Fine-Tuning (SFT)

First, the base model is fine-tuned on a dataset of human-written
instruction-response pairs. This teaches the model the format of
following instructions.

### 2. Reward Model Training

Human labelers rank multiple outputs from the SFT model for the same
prompt. A **reward model** is trained to predict these human preferences.

### 3. Reinforcement Learning

The SFT model is further fine-tuned using **PPO** (Proximal Policy
Optimization) to maximize the reward model's score. The result is a
model optimized for human preferences rather than just next-token
prediction.

### 4. Alignment Trade-offs

The paper documented trade-offs between helpfulness, harmlessness, and
truthfulness. Making a model more harmless can make it less helpful
(over-refusing). This tension remains central to alignment research.

## Impact

- The foundation of ChatGPT (released months after this paper)
- Established RLHF as the standard alignment pipeline
- Demonstrated that smaller aligned models can outperform larger unaligned ones
- Spawned a generation of research on alignment alternatives (DPO, RLAIF,
  Constitutional AI)

## Connections

- **Builds on:** [GPT-3 (Brown 2020)](./brown-2020-gpt3.md), PPO algorithm
- **Led to:** ChatGPT, [DPO (Rafailov 2023)](./rafailov-2023-dpo.md),
  Constitutional AI (Bai 2022)
- **Author:** [Long Ouyang](../../authors/long-ouyang.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Safety & Alignment](../../topics/llm/safety.md)
