# Long Ouyang

| | |
|---|---|
| **Born** | — |
| **Fields** | Machine learning, AI alignment, reinforcement learning |
| **Known for** | InstructGPT / RLHF paper (2022) |

## Biography

Long Ouyang is a machine learning researcher and the lead author of
the InstructGPT paper (2022), which introduced the **RLHF** (Reinforcement
Learning from Human Feedback) pipeline that turned GPT-3 from a text
predictor into a helpful assistant. This alignment approach became the
template for ChatGPT, Claude, and virtually every major aligned LLM.

## Key Contributions

### InstructGPT and RLHF (2022)

Ouyang led the team that developed the three-stage alignment pipeline:
1. Supervised fine-tuning on human demonstrations
2. Reward model training from human preference rankings
3. PPO reinforcement learning to maximize the reward

The key result: a 1.3B parameter InstructGPT model was preferred by
human evaluators over the 175B parameter base GPT-3 — showing that
alignment matters more than raw scale for user experience.

### Alignment Trade-offs

The paper documented the tension between helpfulness, harmlessness, and
truthfulness — a tension that remains central to alignment research.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2022 | "Training language models to follow instructions with human feedback" (InstructGPT, lead author) | Paper | [→](../works/papers/ouyang-2022-instructgpt.md) |

## Influence

### Influenced by

- **Tom Brown et al.** — GPT-3 (2020), the base model for InstructGPT
- **Paul Christiano, Jan Leike** — AI alignment and RLHF research

### Influenced

- **ChatGPT** — directly built on the InstructGPT pipeline
- **DPO** (Rafailov 2023) — simpler alternative to RLHF
- **Constitutional AI** (Bai 2022) — alternative alignment approach
- **The entire aligned LLM industry** — RLHF is now standard

## Related Pages

- [InstructGPT](../works/papers/ouyang-2022-instructgpt.md)
- [Large Language Models](../topics/llm/index.md)
- [Safety & Alignment](../topics/llm/safety.md)
