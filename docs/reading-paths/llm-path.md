# Reading Path — Large Language Models

Goal: understand how LLMs work, how to build with them, and how to
evaluate and deploy them responsibly — from transformer fundamentals
to production RAG pipelines and agent architectures.

## Steps

### 1) The transformer architecture
- Read: [Vaswani et al. — Attention Is All You Need (2017)](../works/papers/vaswani-2017-attention.md)
- Focus: self-attention, multi-head attention, why recurrence was replaced
- Also watch: Karpathy's "Let's build GPT" video for intuition

### 2) Scaling laws
- Read: [Kaplan et al. — Scaling Laws (2020)](../works/papers/kaplan-2020-scaling-laws.md)
- Focus: power-law relationships; optimal allocation of compute, data, parameters
- Also read: Hoffmann et al. — Chinchilla (2022) for revised estimates

### 3) In-context learning and few-shot prompting
- Read: [Brown et al. — GPT-3 (2020)](../works/papers/brown-2020-gpt3.md)
- Focus: emergence of few-shot learning; prompting as an interface

### 4) Alignment and RLHF
- Read: [Ouyang et al. — InstructGPT (2022)](../works/papers/ouyang-2022-instructgpt.md)
- Focus: the three-stage RLHF pipeline; reward models; helpfulness vs. harmlessness
- Also read: Rafailov et al. — DPO (2023) for a simpler alternative

### 5) Reasoning via prompting
- Read: [Wei et al. — Chain-of-Thought (2022)](../works/papers/wei-2022-chain-of-thought.md)
- Focus: step-by-step reasoning; self-consistency; emergent reasoning at scale

### 6) Agents and tool use
- Read: [Yao et al. — ReAct (2022)](../works/papers/yao-2022-react.md)
- Focus: interleaving reasoning and action; tool calling patterns

### 7) Production integration
- Read: [Liu et al. — Lost in the Middle (2023)](../works/papers/liu-2023-lost-in-middle.md)
- Focus: context assembly; RAG design; evaluation and safety
- Also explore: [RAG](../topics/llm/rag.md), [Agents](../topics/llm/agents.md),
  [Evaluation](../topics/llm/evaluation.md), [Safety](../topics/llm/safety.md)

## Practice

Build a small RAG system for a domain you know:
- Choose a dataset (documentation, papers, internal wiki)
- Implement chunking, embedding, retrieval, and generation
- Evaluate retrieval accuracy and answer correctness
- Add reranking and measure improvement
- Test for hallucination by asking questions outside the knowledge base

## Related

- [Large Language Models topic](../topics/llm/index.md)
- [Transformer Architecture deep dive](../topics/llm/transformer.md)
- [Prompting Strategies deep dive](../topics/llm/prompting.md)
- [Distributed Systems topic](../topics/distributed/index.md) — serving models at scale
- [Databases topic](../topics/databases/index.md) — vector stores
