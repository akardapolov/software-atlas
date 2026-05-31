# Lost in the Middle: How Language Models Use Long Contexts

| | |
|---|---|
| **Authors** | Nelson F. Liu, Kevin Lin, John Hewitt, Ashwin Paranjape, Michele Bevilacqua, Fabio Petroni, Percy Liang |
| **Year** | 2023 |
| **Publication** | *arXiv preprint* |
| **Topic(s)** | LLMs, context window, retrieval, RAG |
| **PDF** | [arXiv](https://arxiv.org/abs/2307.03172) |

## Summary

This paper demonstrated that LLMs perform worse on information placed
in the **middle** of long contexts, even when the model's context window
can technically accommodate the entire input. The effect is consistent
across models and tasks: the beginning and end of a prompt are used
more effectively than the middle.

The finding has direct engineering implications for RAG systems,
prompt architecture, and any application that assembles long contexts
from multiple sources.

## Key Ideas

### 1. U-Shaped Performance Curve

When relevant information is placed at the beginning or end of a long
context, retrieval accuracy is high. When placed in the middle,
accuracy drops significantly — often to near-random levels.

### 2. Task: Multi-Document QA

The authors constructed a task where the answer to a question is in
one of several documents provided in the context. By varying the
position of the relevant document, they measured how well models
use information at different positions.

### 3. Model-Agnostic

The effect was observed across multiple models (GPT-3.5, GPT-4,
Claude, LLaMA), suggesting it is a fundamental property of how
transformers process long sequences, not a quirk of any specific
architecture.

### 4. Implications for RAG

When assembling retrieved chunks into a prompt:
- Place the most relevant chunks at the **beginning or end**
- Avoid burying critical information in the middle
- Consider reranking by relevance before context assembly

## Impact

- Changed best practices for RAG context assembly
- Influenced prompt architecture design
- Motivated research on improved attention mechanisms for long context
- Led to techniques like hierarchical summarization to compress middle content

## Connections

- **Builds on:** [Attention (Vaswani 2017)](./vaswani-2017-attention.md),
  [GPT-3 (Brown 2020)](./brown-2020-gpt3.md)
- **Related to:** RAG systems, prompt engineering, long-context models
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [RAG](../../topics/llm/rag.md) ·
  [Prompting Strategies](../../topics/llm/prompting.md)
