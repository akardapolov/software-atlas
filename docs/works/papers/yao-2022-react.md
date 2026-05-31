# ReAct: Synergizing Reasoning and Acting in Language Models

| | |
|---|---|
| **Authors** | Shunyu Yao, Jeffrey Zhao, Dian Yu, Nan Du, Izhak Shafran, Karthik Narasimhan, Yuan Cao |
| **Year** | 2022 |
| **Publication** | *International Conference on Learning Representations (ICLR)* |
| **Topic(s)** | LLMs, agents, tool use, reasoning |
| **PDF** | [arXiv](https://arxiv.org/abs/2210.03629) |

## Summary

ReAct introduced a paradigm for LLM agents that **interleaves reasoning
(Thought) and action (Act)** steps. Unlike chain-of-thought, which only
reasons, ReAct reasons about what action to take, executes it, observes
the result, and reasons again. This loop enables LLMs to interact with
external tools, APIs, and environments.

The paper demonstrated that combining reasoning and acting outperforms
either alone: reasoning-only (chain-of-thought) lacks grounding in
external information, while acting-only lacks the planning that
prevents wasted or incorrect tool calls.

## Key Ideas

### 1. Thought-Action-Observation Loop

```
Question: What is the current price of Apple stock?

Thought: I need to find the current price of AAPL.
Action: search("AAPL stock price today")
Observation: AAPL is trading at $213.42 as of market close.

Thought: I have the price. I can now answer the question.
Answer: Apple's stock closed at $213.42 today.
```

Each cycle appends to the context, so the model conditions each step
on everything that has happened.

### 2. Synergy of Reasoning and Acting

Reasoning improves action selection by planning ahead. Acting grounds
reasoning in real information rather than internal knowledge. The
combination is greater than the sum of parts.

### 3. Task Domains

The paper evaluated ReAct on:
- **Question answering** (HotPotQA) — multi-hop reasoning with Wikipedia
- **Fact verification** (Fever) — retrieving evidence for claims
- **Interactive decision-making** (ALFWorld) — navigating virtual environments

## Impact

- Foundation of the LLM agent paradigm
- Directly influenced LangChain, AutoGPT, and agent frameworks
- Established the standard pattern for tool use in LLM systems
- Led to multi-agent systems and autonomous agent research

## Connections

- **Builds on:** [Chain-of-Thought (Wei 2022)](./wei-2022-chain-of-thought.md),
  [GPT-3 (Brown 2020)](./brown-2020-gpt3.md)
- **Led to:** AutoGPT, LangChain agents, multi-agent systems,
  [Lost in the Middle (Liu 2023)](./liu-2023-lost-in-middle.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Agents & Tool Use](../../topics/llm/agents.md) ·
  [Prompting Strategies](../../topics/llm/prompting.md)
