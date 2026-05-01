/*
 * OOP and Modules in TypeScript
 * =========================
 * Demonstrates classes, interfaces, inheritance, generics,
 * access modifiers, and modules. TypeScript adds static typing to JavaScript.
 */

// ── Classes and Objects ────────────────────────────

function demonstrateClassesObjects(): void {
  console.log("--- Classes and Objects ---");

  // Class definition
  class Animal {
    constructor(public name: string, public species: string) {}

    speak(): string {
      return `${this.name} makes a sound`;
    }
  }

  // Object instantiation
  const dog = new Animal("Buddy", "Dog");
  const cat = new Animal("Whiskers", "Cat");

  console.log(`dog = ${dog}`);
  console.log(`dog.speak() = ${dog.speak()}`);

  // Class vs Instance attributes
  class Counter {
    count: number = 0;  // Class attribute (shared)

    constructor() {
      // Reset instance attribute
      this.value = 0;
    }

    increment(): void {
      this.count++;
      this.value++;
    }
  }

  const counter1 = new Counter();
  const counter2 = new Counter();
  counter1.increment();
  counter2.increment();

  console.log(`counter1.count = ${counter1.count} (class attribute)`);
  console.log(`counter2.count = ${counter2.count} (class attribute)`);
  console.log(`counter1.value = ${counter1.value} (instance attribute)`);
  console.log(`counter2.value = ${counter2.value} (instance attribute)`);

  // Instance methods vs Class methods
  class InstanceMethods {
    instanceMethod(): string {
      return "Instance method";
    }

    static classMethod(): string {
      return "Class method";
    }
  }

  const obj = new InstanceMethods();
  console.log(`obj.instanceMethod() = ${obj.instanceMethod()}`);
  console.log(`obj.classMethod() = ${obj.classMethod()}`);
}


// ── Inheritance ───────────────────────────────

function demonstrateInheritance(): void {
  console.log("\n--- Inheritance ---");

  // Base class
  class Vehicle {
    constructor(public brand: string, public year: number) {}

    start(): string {
      return `${this.brand} engine starting`;
    }

    stop(): string {
      return `${this.brand} engine stopping`;
    }
  }

  // Subclass with method override
  class Car extends Vehicle {
    constructor(brand: string, year: number, private model: string) {}

    start(): string {
      return `${this.brand} ${this.model} starting`;
    }

    stop(): string {
      return `${this.brand} ${this.model} stopping`;
    }
  }

  // Subclass with additional methods
  class ElectricCar extends Car {
    constructor(brand: string, year: number, model: string, private batteryCapacity: number) {}

    charge(): string {
      return `${this.brand} ${this.model} charging`;
    }

    getRange(): number {
      return this.batteryCapacity * 3;
    }
  }

  // Inheritance demonstration
  const tesla = new ElectricCar("Tesla", 2020, "Model S", 400);
  const f150 = new Car("Ford", 2020, "F-150");

  console.log(`tesla.start() = ${tesla.start()}`);
  console.log(`tesla.charge() = ${tesla.charge()}`);
  console.log(`tesla.getRange() = ${tesla.getRange()} miles`);

  console.log(`f150.start() = ${f150.start()}`);
  console.log(`f150.charge() = N/A (no battery)`);
  console.log(`f150.getRange() = N/A (no battery)`);

  // instanceof and is subclass checks
  console.log(`tesla is a Vehicle? ${tesla instanceof Vehicle}`);
  console.log(`tesla is a Car? ${tesla instanceof Car}`);
  console.log(`ElectricCar is subclass of Car? ${tesla instanceof ElectricCar}`);

  // Protected access (TypeScript 3.8+)
  console.log(`tesla.model = ${tesla['model']}`);
}


// ── Access Modifiers ─────────────────────

function demonstrateAccessModifiers(): void {
  console.log("\n--- Access Modifiers ---");

  // Public (default)
  class PublicClass {
    public publicField: string = "public";
    public publicMethod(): string {
      return "public method";
    }
  }

  // Private
  class PrivateClass {
    private privateField: string = "private";
    private privateMethod(): string {
      return "private method";
    }
  }

  // Protected
  class ProtectedClass {
    protected protectedField: string = "protected";
    protected protectedMethod(): string {
      return "protected method";
    }

    // Read-only (readonly)
  class ReadonlyClass {
    readonly readonlyField: string = "readonly";

    public constructor(readonlyField: string) {
      this['readonlyField'] = readonlyField;
    }
  }

  const pubInst = new PublicClass();
  const privInst = new PrivateClass();
  const protInst = new ProtectedClass();

  console.log(`Public field: ${pubInst.publicField}`);
  console.log(`Private field (inaccessible): ${privInst['privateField']}`);
  console.log(`Protected field (accessible): ${protInst.protectedField}`);
  console.log(`Readonly field: ${pubInst['readonlyField']}`); // Can read, write via constructor

  // Static members
  class StaticMembers {
    public static staticField: string = "static field";
    public static staticMethod(): string {
      return "static method";
    }
  }

  console.log(`StaticMembers.staticField = ${StaticMembers.staticField}`);
  console.log(`StaticMembers.staticMethod() = ${StaticMembers.staticMethod()}`);

  const instance = new StaticMembers();
  console.log(`instance.staticField = ${instance['staticField']}`); // Can access static via instance
  console.log(`instance.staticMethod() = ${instance.staticMethod()}`);
}


// ── Abstract Classes ─────────────────────

function demonstrateAbstractClasses(): void {
  console.log("\n--- Abstract Classes ---");

  // Abstract class (cannot be instantiated)
  abstract class Shape {
    abstract area(): number;
    abstract perimeter(): number;
  }

  // Concrete class
  class Rectangle extends Shape {
    constructor(public width: number, public height: number) {}

    area(): number {
      return this.width * this.height;
    }

    perimeter(): number {
      return 2 * (this.width + this.height);
    }
  }

  // Cannot instantiate abstract class
  // const shape = new Shape();  // Compile error!

  // Instantiate concrete subclasses
  const rectangle = new Rectangle(5, 3);
  const circle = new Circle(2);

  console.log(`rectangle.area() = ${rectangle.area()}`);
  console.log(`circle.area() = ${circle.area()}`);
}


// ── Interfaces ─────────────────────────────

function demonstrateInterfaces(): void {
  console.log("\n--- Interfaces ---");

  // Interface definition
  interface Drawable {
    draw(): void;
    resize(factor: number): void;
  }

  // Class implementing interface
  class Circle implements Drawable {
    constructor(private radius: number) {}

    draw(): void {
      console.log(`Drawing circle with radius ${this.radius}`);
    }

    resize(factor: number): void {
      this.radius *= factor;
    }
  }

  class Rectangle implements Drawable {
    constructor(private width: number, private height: number) {}

    draw(): void {
      console.log(`Drawing rectangle ${this.width}x${this.height}`);
    }

    resize(factor: number): void {
      this.width *= factor;
      this.height *= factor;
    }
  }

  // Polymorphic usage
  const shape1: Drawable = new Circle(5);
  const shape2: Drawable = new Rectangle(10, 5);

  function drawAll(drawable: Drawable): void {
    drawable.draw();
  }

  drawAll(shape1);
  drawAll(shape2);

  // Multiple interfaces
  interface Swimmable {
    swim(): void;
  }

  interface Flyable {
    fly(): void;
  }

  class Duck implements Swimmable, Flyable {
    swim(): void {
      console.log("Duck swimming");
    }

    fly(): void {
      console.log("Duck flying!");
    }
  }

  const duck = new Duck();

  duck.swim();
  duck.fly();
}


// ── Properties (getters/setters) ─────────────

function demonstrateProperties(): void {
  console.log("\n--- Properties (getters/setters) ---");

  class Temperature {
    private _celsius: number;

    get celsius(): number {
      return this._celsius;
    }

    set celsius(value: number): void {
      this._celsius = value;
    }

    get fahrenheit(): number {
      return this._celsius * 9 / 5 + 32;
    }

    set fahrenheit(value: number): void {
      this._celsius = (value - 32) * 5 / 9;
    }
  }

  const temp = new Temperature();
  temp.celsius = 25;

  console.log(`temp.celsius = ${temp.celsius}`);
  console.log(`temp.fahrenheit = ${temp.fahrenheit}`);

  temp.fahrenheit = 77;
  console.log(`After setting F=77, C = ${temp.celsius}`);
}


// ── Generics ─────────────────────────────

function demonstrateGenerics(): void {
  console.log("\n--- Generics ---");

  // Generic class
  class Box {
    public value: T;

    constructor(value: T) {
      this.value = value;
    }
  }

  // Generic interface
  interface Repository {
    get(id: string): T;
    save(entity: T): void;
  }

  // Generic function
  function first<T>(array: T[]): T {
    return array[0];
  }

  const stringBox = new Box<string>("hello");
  const numberBox = new Box<number>(42);

  console.log(`stringBox.value = ${stringBox.value}`);
  console.log(`numberBox.value = ${numberBox.value}`);

  console.log(`first<string>(["a", "b"]) = ${first<string>(["a", "b"])}`);
  console.log(`first<number>([1, 2, 3]) = ${first<number>([1, 2, 3])}`);
}


// ── Method Parameters ─────────────────────

function demonstrateMethodParameters(): void {
  console.log("\n--- Method Parameters ---");

  // Optional parameters
  function greet(name: string, greeting?: string): string {
    return `${greeting}, ${name}!`;
  }

  // Default parameter values
  function multiply(a: number, b: number = 1): number {
    return a * b;
  }

  // Rest parameters
  function sum(...numbers: number[]): number {
    return numbers.reduce((acc, n) => acc + n, 0);
  }

  console.log(`greet("Alice") = ${greet("Alice")}`);
  console.log(`greet("Alice", "Hi") = ${greet("Alice", "Hi")}`);
  console.log(`multiply(5, 3) = ${multiply(5, 3)}`);
  console.log(`sum(1,2,3,4,5) = ${sum(1, 2, 3, 4, 5)}`);
}


// ── Enums ─────────────────────────────

function demonstrateEnums(): void {
  console.log("\n--- Enums ---");

  // Numeric enum
  enum Direction {
    Up,
    Down,
    Left,
    Right,
  }

  // String enum
  enum Color {
    Red,
    Green,
    Blue,
  }

  // Heterogeneous enum with values
  enum Status {
    Active = 200,
    Pending,
    Failed = { message: string, code: number },
  }

  // Enum usage
  const dir: Direction = Direction.Up;
  const color: Color = Color.Red;
  const status: Status.Status = Status.Active;

  console.log(`Direction: ${dir}`);
  console.log(`Color: ${color}`);
  console.log(`Status: ${status}`);

  // Enum members
  console.log(`All Directions: ${Object.values(Direction)}`);

  // Numeric enum values
  console.log(`Direction.Up value: ${Direction.Up}`);
}


// ── Decorators ───────────────────────────

function demonstrateDecorators(): void {
  console.log("\n--- Decorators ---");

  // Class decorator
  @sealed()
  class Animal {}

  @sealed()
  class Mammal extends Animal {}

  @sealed()
  class Reptile extends Mammal {}

  // Method decorator
  @log
  class Logger {
    @log
    private log(message: string): void {
      console.log(`[LOG] ${message}`);
    }
  }

  const logger = new Logger();

  // @deprecated
  class OldAPI {
    @deprecated("Use NewAPI instead")
    oldMethod(): string {
      return "old method";
    }

    newMethod(): string {
      return "new method";
    }
  }

  const api = new OldAPI();

  console.log("Using oldMethod (should warn):");
  api.oldMethod();

  console.log("Using newMethod (no warning):");
  api.newMethod();

  // Property decorator
  function readonly(target: any, key: string) {
    return {
      writable: false,
      configurable: false,
      get(): any {
        return target[key];
      }
    };
  }

  class Config {
    @readonly
    version: string = "1.0.0";

    @readonly
    apiUrl: string = "https://api.example.com";
  }

  const config = new Config();

  console.log(`config.version = ${config.version}`);
  console.log(`config.apiUrl = ${config.apiUrl}`);

  // config.version = "2.0.0";  // Error! readonly
}


// ── Modules (namespaces) ─────────────────

function demonstrateModules(): void {
  console.log("\n--- Modules (namespaces) ---");

  // Export default
  export class PublicClass {
    public value: string = "exported class";
  }

  export const constant = "exported constant";

  // Import named
  import { Logger } from "./logger";

  // Import all (wildcard)
  import * as utils from "./utils";

  // Default export
  export default class DefaultExport {
    public value: string = "default export";
  }

  // Namespace export
  export namespace MyNamespace {
    export class NamespacedClass {
      public value: string = "namespaced class";
    }
  }

  // Re-export
  export { PublicClass, constant, DefaultExport } from "./re-export";

  // Type-only imports
  import type { Animal } from "./types";

  // Ambient modules
  declare const GlobalValue: string;

  console.log(`GlobalValue = ${GlobalValue}`);
}


// ── Summary ───────────────────────────────────

function main(): void {
  console.log("OOP and Modules in TypeScript");
  console.log("==========================");

  demonstrateClassesObjects();
  demonstrateInheritance();
  demonstrateAccessModifiers();
  demonstrateAbstractClasses();
  demonstrateInterfaces();
  demonstrateProperties();
  demonstrateGenerics();
  demonstrateMethodParameters();
  demonstrateEnums();
  demonstrateDecorators();
  demonstrateModules();

  console.log("\n--- Summary ---");
  console.log("TypeScript OOP:");
  console.log("  - Classes: class keyword, constructor, public/private");
  console.log("  - Inheritance: extends keyword, method override");
  console.log("  - Multiple inheritance: no support (use composition)");
  console.log("  - Access modifiers: public, private, protected, readonly");
  console.log("  - Abstract classes: abstract keyword, abstract methods");
  console.log("  - Interfaces: contracts with implements keyword");
  console.log("  - Generics: <T> type parameters");
  console.log("  - Enums: enum keyword with values");
  console.log("  - Decorators: @sealed, @log, @deprecated");
  console.log("  - Modules: export/import, namespaces");
  console.log("  - Type inference: automatic with optional static typing");
}
