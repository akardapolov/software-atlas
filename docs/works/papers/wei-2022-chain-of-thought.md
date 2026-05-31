# Chain-of-Thought Prompting Elicits Reasoning in Large Language Models

| | |
|---|---|
| **Authors** | Jason Wei, Xuezhi Wang, Dale Schuurmans, Maarten Bosma, Brian Ichter, Fei Xia, Ed Chi, Quoc Le, Denny Zhou |
| **Year** | 2022 |
| **Publication** | *Advances in Neural Information Processing Systems (NeurIPS)* |
| **Topic(s)** | LLMs, prompting, reasoning, multi-step tasks |
| **PDF** | [arXiv](https://arxiv.org/abs/2201.11903) |

## Summary

This paper showed that prompting LLMs to "think step by step" before
giving a final answer dramatically improves performance on multi-step
reasoning tasks — arithmetic, commonsense reasoning, and symbolic
manipulation. The improvement is large (often 2–3×) and emerges only
at sufficient scale.

The key insight is that **intermediate reasoning steps are themselves
tokens**, and the model's next-token prediction can condition on them.
The model is not doing internal symbolic reasoning — it is generating
text that happens to be reasoning, and that text helps it generate
better text next.

## Key Ideas

### 1. Few-Shot Chain-of-Thought

Provide examples that include reasoning steps, not just answers:

```
Q: Roger has 5 tennis balls. He buys 2 more cans of 3 balls each.
How many tennis balls does he have now?

A: Roger starts with 5 balls. He buys 2 cans × 3 balls = 6 balls.
5 + 6 = 11. The answer is 11.

Q: The cafeteria had 23 apples...
```

### 2. Emergence at Scale

Chain-of-thought benefits appear only in sufficiently large models
(around 100B parameters). Smaller models either ignore the reasoning
steps or generate incoherent chains.

### 3. Self-Consistency

Generate multiple reasoning paths and take the most frequent final
answer. This further improves accuracy by exploring the model's
reasoning distribution.

### 4. Task Generality

Chain-of-thought improves performance across diverse reasoning tasks:
- Arithmetic (GSM8K)
- Commonsense (StrategyQA)
- Symbolic manipulation (last letter concatenation)
- Logical reasoning

## Impact

- Established "think step by step" as a standard prompting technique
- Sparked research on reasoning in LLMs (Tree of Thoughts, self-refinement)
- Led to structured reasoning approaches in agent systems
- Demonstrated that prompting strategies can unlock capabilities not
  apparent in base model evaluation

## Connections

- **Builds on:** [GPT-3 (Brown 2020)](./brown-2020-gpt3.md)
- **Led to:** Zero-shot CoT (Kojima 2022), Tree of Thoughts (Yao 2023),
  ReAct (Yao 2022), self-refinement
- **Author:** [Jason Wei](../../authors/jason-wei.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Prompting Strategies](../../topics/llm/prompting.md)
