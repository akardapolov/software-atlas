# Language Models are Few-Shot Learners

| | |
|---|---|
| **Authors** | Tom B. Brown, Benjamin Mann, Nick Ryder, Melanie Subbiah, Jared Kaplan, Prafulla Dhariwal, Arvind Neelakantan, Pranav Shyam, Girish Sastry, Amanda Askell, Sandhini Agarwal, Ariel Herbert-Voss, Gretchen M. Krueger, Tom Henighan, Rewon Child, Aditya Ramesh, Daniel Ziegler, Jeffrey Wu, Clemens Winter, Chris Hesse, Mark Chen, Eric Sigler, Mateusz Litwin, Scott Gray, Benjamin Chess, Jack Clark, Christopher Berner, Sam McCandlish, Alec Radford, Ilya Sutskever, Dario Amodei |
| **Year** | 2020 |
| **Publication** | *Advances in Neural Information Processing Systems (NeurIPS)* |
| **Topic(s)** | LLMs, in-context learning, few-shot prompting |
| **PDF** | [arXiv](https://arxiv.org/abs/2005.14165) |

## Summary

The GPT-3 paper demonstrated that scaling language models to 175 billion
parameters unlocks **in-context learning**: the ability to learn a new
task from just a few examples provided in the prompt, without any
gradient updates or fine-tuning.

This was a qualitative shift. Previous models needed task-specific
training. GPT-3 could be given three examples of sentiment classification
and perform the task — simply by predicting what comes next in the
sequence.

## Key Ideas

### 1. Few-Shot Learning

Provide a small number of input-output examples in the prompt. The model
infers the pattern and applies it to new inputs:

```
Translate English to French:
sea otter => loutre de mer
peppermint => menthe poivrée
plush girafe => girafe peluche
cheese =>
```

No fine-tuning. No gradient descent. The model "learns" the task from
the prompt alone.

### 2. Zero-Shot and One-Shot

GPT-3 also demonstrated strong zero-shot (no examples) and one-shot
(single example) performance. Few-shot was generally best, but even
zero-shot often outperformed fine-tuned models from prior years.

### 3. Emergent Capabilities

At 175B parameters, capabilities emerged that were not present in smaller
models: arithmetic, code generation, translation, summarization — all
without task-specific training. This suggested that scale unlocks
qualitatively new behaviors.

### 4. API-First Deployment

GPT-3 was deployed as an API rather than released as open weights. This
established the commercial model for LLM deployment that dominates today.

## Impact

- Established the paradigm of prompting as the primary interface to LLMs
- Demonstrated that scale produces emergent capabilities
- Sparked the commercial LLM industry (OpenAI API)
- Led directly to GPT-3.5, GPT-4, and the current generation of models

## Connections

- **Builds on:** [Scaling Laws (Kaplan 2020)](./kaplan-2020-scaling-laws.md),
  [Attention (Vaswani 2017)](./vaswani-2017-attention.md)
- **Led to:** [InstructGPT (Ouyang 2022)](./ouyang-2022-instructgpt.md),
  [Chain-of-Thought (Wei 2022)](./wei-2022-chain-of-thought.md),
  ChatGPT, GPT-4
- **Author:** [Tom Brown](../../authors/tom-brown.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Prompting Strategies](../../topics/llm/prompting.md)
