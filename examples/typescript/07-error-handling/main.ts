// Error Handling in TypeScript
// ======================
// try-catch-finally, throw, custom errors, never, unknown

// ── Custom error types ───────────────────────────

class ValidationError extends Error {
  constructor(
    public message: string,
    public code: number
  ) {
    super(message);
    this.code = code;
    this.name = 'ValidationError';
  }
}

class BusinessError extends Error {
  constructor(public message: string) {
    super(message);
    this.name = 'BusinessError';
  }
}

// ── Functions that throw errors ─────────────────────

function validateEmail(email: string): never {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    throw new ValidationError('Invalid email format', 400);
  }
}

function divide(a: number, b: number): number {
  if (b === 0) {
    throw new BusinessError('Division by zero');
  }
  return a / b;
}

// ── try-catch-finally ───────────────────────────

function demoTryFinally(): void {
  console.log('--- try-catch-finally ---');

  let fileCreated = false;

  try {
    throw new Error('Intentional error');
  } catch (e) {
    console.log('Caught:', e.message);
  } finally {
    fileCreated = true;
    console.log('Finally executed');
  }

  console.log('File created:', fileCreated);
}

// ── Catch specific exception types ───────────────

function demoCatchSpecific(): void {
  console.log('\n--- Catch specific types ---');

  try {
    validateEmail('invalid');
  } catch (e) {
    if (e instanceof ValidationError) {
      console.log('Caught ValidationError:', e.message);
    }
  }

  try {
    divide(10, 0);
  } catch (e) {
    if (e instanceof BusinessError) {
      console.log('Caught BusinessError:', e.message);
    }
  }
}

// ── Multi-catch with type guards ─────────────

function demoMultiCatch(): void {
  console.log('\n--- Multi-catch ---');

  try {
    throw new TypeError('Type error');
  } catch (e: unknown) {
    console.log('Caught unknown type:', e.message);
  }
}

// ── Async error handling ───────────────────────────────

async function asyncError(): Promise<never> {
  console.log('\n--- Async errors ---');

  throw new Error('Async error');
}

async function handleAsyncError(): Promise<void> {
  try {
    await asyncError();
  } catch (e) {
    console.log('Caught async error:', e.message);
  }
}

// ── Promise rejection ─────────────────────────────

function demoPromiseRejection(): Promise<void> {
  console.log('\n--- Promise rejection ---');

  Promise.reject(new Error('Intentional rejection'))
    .catch(e => console.log('Caught rejection:', e.message));
}

// ── Type narrowing for errors ───────────────────────

function handleSpecificError(error: ValidationError | BusinessError): string {
  if (error instanceof ValidationError) {
    return `Validation: ${error.code} - ${error.message}`;
  }
  if (error instanceof BusinessError) {
    return `Business: ${error.message}`;
  }
  return `Unknown error: ${error.message}`;
}

function demoTypeNarrowing(): void {
  console.log('\n--- Type narrowing ---');

  try {
    validateEmail('test@example.com');
  } catch (e) {
    console.log(handleSpecificError(e));
  }

  try {
    divide(10, 0);
  } catch (e) {
    console.log(handleSpecificError(e));
  }
}

// ── Optional chaining ───────────────────────────────

function getValueOrThrow(value: string | null): string {
  if (!value) {
    throw new ValidationError('Value is required', 400);
  }
  return value;
}

function demoOptionalChaining(): void {
  console.log('\n--- Optional chaining ---');

  const optional: string | null = null;
  const result = optional || getValueOrThrow('default');
  console.log('Result:', result);
}

// ── never type (unreachable) ───────────────────────

function unreachable(value: never): never {
  console.log('This should never execute');
  throw new Error('Unreachable code');
}

function demoNever(): void {
  console.log('\n--- never type ---');

  // TypeScript ensures unreachable code paths
  const val: unknown = 'some value';

  if (val === 'some value') {
    unreachable(val);
  }
}

// ── Main ───────────────────────────────────────────

async function main(): Promise<void> {
  demoTryFinally();
  demoCatchSpecific();
  demoMultiCatch();
  demoTypeNarrowing();
  await handleAsyncError();
  demoPromiseRejection();
  demoOptionalChaining();
  demoNever();

  console.log('\n--- Summary ---');
  console.log('TypeScript error handling:');
  console.log('  - try-catch-finally: structured error handling');
  console.log('  - throw: throw new Error("message")');
  console.log('  - Custom errors: extend Error interface');
  console.log('  - Type guards: instanceof SpecificError');
  console.log('  - Multi-catch: catch (TypeError | ...)');
  console.log('  - Async: await try/catch for async functions');
  console.log('  - Promise: reject(err) for errors');
  console.log('  - Type narrowing: discriminated unions in catch');
  console.log('  - never: compile-time unreachable code paths');
  console.log('  - Optional: || operator for null coalescing');
}
