# Direct Preference Optimization: Your Language Model is Secretly a Reward Model

| | |
|---|---|
| **Authors** | Rafael Rafailov, Archit Sharma, Eric Mitchell, Christopher D. Manning, Stefano Ermon, Chelsea Finn |
| **Year** | 2023 |
| **Publication** | *Advances in Neural Information Processing Systems (NeurIPS)* |
| **Topic(s)** | LLMs, alignment, preference learning, RLHF alternative |
| **PDF** | [arXiv](https://arxiv.org/abs/2305.18290) |

## Summary

DPO (Direct Preference Optimization) showed that the three-stage RLHF
pipeline — supervised fine-tuning, reward model training, and
reinforcement learning — can be replaced with a single classification
objective over preference pairs. The language model itself serves as
the implicit reward model.

The result: simpler, more stable training that achieves comparable
alignment quality to RLHF without the complexity of reinforcement
learning or the need for a separate reward model.

## Key Ideas

### 1. The RLHF Pipeline is Unnecessary

RLHF requires:
1. SFT on human demonstrations
2. Training a reward model from human preferences
3. PPO reinforcement learning against the reward model

DPO shows that steps 2 and 3 can be combined into a single
classification loss over preference pairs.

### 2. Implicit Reward Model

The key insight: the language model's probabilities already encode a
reward function. If the model assigns higher probability to a preferred
response than a dispreferred one, that preference is implicitly captured.

### 3. Simpler and More Stable

- No separate reward model to train
- No PPO hyperparameters to tune
- No reward hacking (the reward is implicit, not explicit)
- Comparable or better alignment results

### 4. Limitations

DPO works best when the preference data is high-quality and diverse.
It can overfit to the preference dataset and may not generalize as well
as RLHF to out-of-distribution inputs.

## Impact

- Simplified alignment pipelines for many open-source models
- Adopted by Llama 2, Mistral, and other open-weight models
- Sparked research on alignment without reinforcement learning
- Demonstrated that simpler methods can match complex pipelines

## Connections

- **Builds on:** [InstructGPT / RLHF (Ouyang 2022)](./ouyang-2022-instructgpt.md)
- **Related to:** Constitutional AI, RLAIF, preference learning
- **Author:** [Rafael Rafailov et al.](../../authors/index.md)
- **Related topic:** [Large Language Models](../../topics/llm/index.md) ·
  [Safety & Alignment](../../topics/llm/safety.md)
