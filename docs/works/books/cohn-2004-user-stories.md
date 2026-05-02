# User Stories Applied: For Agile Software Development

| | |
|---|---|
| **Author** | Mike Cohn |
| **Year** | 2004 |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | User stories, agile, requirements |
| **ISBN** | 978-0-321-20565-5 |

## Summary

Cohn provided a comprehensive guide to **user stories** — short,
simple descriptions of software features from the user's perspective.
The book established user stories as the standard way to capture
requirements in agile development.

## Key Ideas

### User Story Format

Cohn codified the standard user story format:

```
As a <type of user>,
I want <some goal>,
So that <some benefit>.
```

**Example:**
```
As a customer,
I want to save my payment methods,
So that I don't have to enter them every time I check out.
```

The format ensures stories are:
- **User-centered** — always written from user's perspective
- **Value-oriented** — explains why feature matters
- **Action-oriented** — describes what user wants to do

### INVEST Criteria

Cohn introduced the INVEST framework for evaluating user stories:

| Criterion | Meaning | Question |
|-----------|----------|-----------|
| **Independent** | Can be implemented separately | Does this story depend on others? |
| **Negotiable** | Details can be discussed and changed | Is this open to discussion? |
| **Valuable** | Delivers value to users or business | Does this matter to someone? |
| **Estimable** | Team can estimate effort reliably | Can we estimate this? |
| **Small** | Can be completed in one iteration | Is this appropriately sized? |
| **Testable** | Success can be verified objectively | How will we know it's done? |

### Acceptance Criteria

Each user story should include **acceptance criteria** — specific
conditions that must be met for the story to be considered complete.

**Example:**
```
User Story: Save payment method

Acceptance Criteria:
- User can add credit card with valid number
- User can add multiple payment methods
- User can set default payment method
- Expiry date is validated
- Card number is masked after entry
```

Benefits:
- Clear definition of "done"
- Testable requirements
- Reduces ambiguity
- Enables independent implementation

### Story Points

Cohn clarified the concept of **story points** as relative,
not absolute, measures of effort:

**Key principles:**
- Story points measure complexity, risk, and effort — not hours
- Points are relative to each other, not to some absolute scale
- Different teams have different point scales
- Points enable velocity tracking

**Planning with story points:**
```
Sprint planning:
Team capacity: 30 points

Backlog:
- Login page: 1 point
- User profile: 3 points
- Payment integration: 5 points

Selected for sprint: 29 points (close to capacity)
```

### Planning Poker

Cohn described **planning poker** as a consensus-based
estimation technique:

**Process:**
1. Product owner reads user story
2. Team asks clarifying questions
3. Each member selects a card secretly
4. Cards revealed simultaneously
5. Highest and lowest explain their reasoning
6. Team discusses and re-estimates
7. Repeat until consensus

**Why planning poker works:**
- Prevents anchoring bias (no one person leads)
- Encourages discussion about complexity
- Reveals different understandings
- Builds team ownership of estimates

### Velocity

Cohn explained **velocity** as a measure of team throughput:

```
Velocity calculation:
Sprint 1: 28 points completed
Sprint 2: 32 points completed
Sprint 3: 26 points completed
Sprint 4: 30 points completed

Average velocity: 29 points/sprint
```

**Using velocity for planning:**
- Predict future capacity based on history
- Plan sprints that match team's sustainable pace
- Detect process problems (sudden drops in velocity)

**Important:** Velocity is a **metric for planning**, not a target
to increase.

### Story Mapping

Cohn introduced **story mapping** as a technique for
visualising product development:

```
Story Map:
[MVP] → [Release 1] → [Release 2] → [Future]

User Journey:
Login → Browse → Add to Cart → Checkout → Confirmation

Stories for each activity, arranged across releases:
┌─────────────────────────────────────────────┐
│ MVP │ Release 1 │ Release 2 │ Future │
│─────┼────────────┼────────────┼──────────│
│ S   │ S           │            │ S        │
│─────┼────────────┼────────────┼──────────│
│ S   │ S           │ S          │          │
└─────────────────────────────────────────────┘
```

**Benefits of story mapping:**
- See full user journey at once
- Identify gaps in understanding
- Plan releases based on value delivery
- Align team on product direction

## Historical Context

### Evolution from Use Cases

User stories evolved from **use cases** (Ivar Jacobson, 1992):

| Aspect | Use Cases | User Stories |
|---------|-------------|--------------|
| **Origin** | OOP analysis | Agile practices |
| **Length** | Detailed documents | Short descriptions |
| **Format** | Actor, goal, flows | As-I-want-So that |
| **Timing** | All upfront | Continuous discovery |
| **Detail** | Complete scenarios | High-level, details later |

### Agile Context

Cohn's work emerged from early agile practices:
- **XP** (Kent Beck) introduced user story-like descriptions
- **Scrum** used backlog and user stories for planning
- Cohn systematised and refined these practices

## Impact and Legacy

### Standardisation of User Stories

*User Stories Applied* established user stories as the standard
for requirements in agile development:
- INVEST criteria became universal
- Story mapping became common practice
- Planning poker became standard estimation technique

### Influence on Product Management

Cohn's approach influenced:
- **Product backlog management** — how to organise and prioritise
- **Release planning** — using velocity to predict delivery
- **User-centered design** — focus on user value, not features

## Connections

- **Builds on:** XP user stories · Use cases (Jacobson)
- **Complements:** Scrum planning · Agile estimation practices
- **Author:** [Mike Cohn](../../authors/mike-cohn.md)
- **Related topic:** [Requirements & Planning](../../topics/process/index.md) · [Agile](../../topics/process/index.md)
