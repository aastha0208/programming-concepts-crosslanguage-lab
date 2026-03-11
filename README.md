# programming-concepts-crosslang

A structured learning resource for understanding core programming concepts across **Java, Python, JavaScript, and C#** side by side.

Built as a companion to [java-practice-programs](https://github.com/aastha0208/java-practice-programs) — Java is the primary language, with every concept mapped to its equivalent in the other three languages.

---

## Structure

```
programming-concepts-crosslang/
├── java/
│   ├── basics/          ← 8 Java fundamentals programs (T01–T08)
│   └── crosslang/       ← 5 Java cross-language comparison programs (CL01–CL05)
├── python/              ← 5 Python companion programs (CL01–CL05)
├── javascript/          ← 5 JavaScript companion programs (CL01–CL05)
└── csharp/              ← 5 C# companion programs (CL01–CL05)
```

---

## How to Run

### Java
```bash
# Compile all
javac java/basics/*.java java/crosslang/*.java

# Run any file (example)
java -cp . basics.T01_DataTypes
java -cp . crosslang.CL01_TypeSystem
```

### Python
```bash
python3 python/CL01_TypeSystem.py
```

### JavaScript (requires Node.js)
```bash
node javascript/CL01_TypeSystem.js
```

### C# (requires .NET SDK)
```bash
# Option 1: dotnet-script (install: dotnet tool install -g dotnet-script)
dotnet-script csharp/CL01_TypeSystem.cs

# Option 2: Create a console app
dotnet new console -n TypeSystem
# Copy file contents into Program.cs, then:
dotnet run
```

---

## Topic Index

| # | Topic | Java (Basics) | Java (CrossLang) | Python | JavaScript | C# |
|---|-------|--------------|-----------------|--------|------------|-----|
| 1 | Data Types & Variables | [T01_DataTypes.java](java/basics/T01_DataTypes.java) | [CL01_TypeSystem.java](java/crosslang/CL01_TypeSystem.java) | [CL01_TypeSystem.py](python/CL01_TypeSystem.py) | [CL01_TypeSystem.js](javascript/CL01_TypeSystem.js) | [CL01_TypeSystem.cs](csharp/CL01_TypeSystem.cs) |
| 2 | Operators | [T02_Operators.java](java/basics/T02_Operators.java) | — | — | — | — |
| 3 | Control Flow | [T03_ControlFlow.java](java/basics/T03_ControlFlow.java) | — | — | — | — |
| 4 | Arrays | [T04_ArrayBasics.java](java/basics/T04_ArrayBasics.java) | — | — | — | — |
| 5 | Strings | [T05_StringBasics.java](java/basics/T05_StringBasics.java) | [CL02_Strings.java](java/crosslang/CL02_StringsAcrossLanguages.java) | [CL02_Strings.py](python/CL02_Strings.py) | [CL02_Strings.js](javascript/CL02_Strings.js) | [CL02_Strings.cs](csharp/CL02_Strings.cs) |
| 6 | Collections | [T08_CollectionsBasics.java](java/basics/T08_CollectionsBasics.java) | [CL03_Collections.java](java/crosslang/CL03_CollectionsAcrossLanguages.java) | [CL03_Collections.py](python/CL03_Collections.py) | [CL03_Collections.js](javascript/CL03_Collections.js) | [CL03_Collections.cs](csharp/CL03_Collections.cs) |
| 7 | OOP | [T06_OOPBasics.java](java/basics/T06_OOPBasics.java) | [CL04_OOP.java](java/crosslang/CL04_OOPAcrossLanguages.java) | [CL04_OOP.py](python/CL04_OOP.py) | [CL04_OOP.js](javascript/CL04_OOP.js) | [CL04_OOP.cs](csharp/CL04_OOP.cs) |
| 8 | Exceptions | [T07_ExceptionHandling.java](java/basics/T07_ExceptionHandling.java) | [CL05_Exceptions.java](java/crosslang/CL05_ExceptionsAcrossLanguages.java) | [CL05_Exceptions.py](python/CL05_Exceptions.py) | [CL05_Exceptions.js](javascript/CL05_Exceptions.js) | [CL05_Exceptions.cs](csharp/CL05_Exceptions.cs) |

---

## Quick Language Comparison

| Concept | Java | Python | JavaScript | C# |
|---------|------|--------|------------|-----|
| Typing | Static, strong | Dynamic, strong | Dynamic, weak | Static, strong |
| `null` equivalent | `null` | `None` | `null` / `undefined` | `null` |
| String format | `String.format()` | `f"..."` | `` `${...}` `` | `$"..."` |
| List | `ArrayList<T>` | `list` | `Array` | `List<T>` |
| Map | `HashMap<K,V>` | `dict` | `Map` / `Object` | `Dictionary<K,V>` |
| Set | `HashSet<T>` | `set` | `Set` | `HashSet<T>` |
| Classes | `class Foo extends Bar` | `class Foo(Bar)` | `class Foo extends Bar` | `class Foo : Bar` |
| Interface | `interface IFoo` | `ABC` / duck typing | TypeScript only | `interface IFoo` |
| Try/Catch | `try/catch/finally` | `try/except/finally` | `try/catch/finally` | `try/catch/finally` |
| Checked exceptions | Yes (unique to Java) | No | No | No |
| Print | `System.out.println()` | `print()` | `console.log()` | `Console.WriteLine()` |

---

## Learning Path

**Step 1 — Java Basics** (start here)
Work through `java/basics/T01` → `T08` in order. Each file is a standalone runnable program with predict-the-output exercises.

**Step 2 — Cross-Language Mapping**
After each Java basics topic, open the corresponding `CL0X` file in all 4 languages side by side. Notice what's identical, what's similar, and what's fundamentally different.

**Suggested pairs:**
- T01 + CL01: Types & Variables
- T05 + CL02: Strings
- T08 + CL03: Collections
- T06 + CL04: OOP
- T07 + CL05: Exceptions

---

## C# is the Closest to Java

If you know Java, C# will feel most familiar:
- Same static typing with same primitive names (`int`, `long`, `double`, `char`)
- Same `class`, `interface`, `abstract class` concepts
- Same `try/catch/finally` syntax
- Same generics (`List<T>`, `Dictionary<K,V>`)
- Key differences: `properties` instead of getters/setters, `virtual`/`override` required, no checked exceptions, `Console.WriteLine()` instead of `System.out.println()`
