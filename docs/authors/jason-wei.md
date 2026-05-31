# Jason Wei

| | |
|---|---|
| **Born** | — |
| **Fields** | Machine learning, natural language processing |
| **Known for** | Chain-of-thought prompting (2022); instruction tuning |

## Biography

Jason Wei is a machine learning researcher known for his work on
**chain-of-thought prompting** and the study of **emergent abilities**
in large language models. He was at Google Brain and later OpenAI,
where he contributed to understanding how and why LLMs develop new
capabilities as they scale.

## Key Contributions

### Chain-of-Thought Prompting (2022)

Wei and co-authors demonstrated that prompting LLMs to "think step by
step" before answering dramatically improves performance on multi-step
reasoning tasks. The improvement is large (often 2–3×) and emerges only
at sufficient scale (around 100B parameters).

The insight: intermediate reasoning steps are themselves tokens, and
the model's next-token prediction can condition on them.

### Emergent Abilities (2022)

Wei cataloged abilities that appear only in sufficiently large models
— abilities not present in smaller versions of the same architecture.
This includes: few-shot learning, chain-of-thought reasoning,
instruction following, and multi-step problem solving.

### Instruction Tuning

Wei contributed to the development of instruction-tuned models —
models trained to follow natural language instructions rather than
just predict text. This work led to FLAN and informed later
instruction-tuned models.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2022 | "Chain-of-Thought Prompting Elicits Reasoning in Large Language Models" | Paper | [→](../works/papers/wei-2022-chain-of-thought.md) |
| 2022 | "Emergent Abilities of Large Language Models" | Paper | — |

## Influence

### Influenced by

- **Tom Brown et al.** — GPT-3 (2020) demonstrated few-shot learning
- **Ashish Vaswani et al.** — Transformer architecture

### Influenced

- **Agent research** — ReAct, Tree of Thoughts build on chain-of-thought
- **Reasoning benchmarks** — MATH, GSM8K designed to test CoT
- **The prompting industry** — "think step by step" is now standard

## Related Pages

- [Chain-of-Thought](../works/papers/wei-2022-chain-of-thought.md)
- [Large Language Models](../topics/llm/index.md)
- [Prompting Strategies](../topics/llm/prompting.md)
- [Agents](../topics/llm/agents.md)
