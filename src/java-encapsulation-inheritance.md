# Java OOP: Encapsulation and Inheritance

Nu när vi har förstått grunderna i klasser och objekt, kan vi fortsätta med två viktiga koncept inom objektorienterad programmering (OOP): **encapsulation** (inkapsling) och **inheritance** (arv).

## Encapsulation - Att skydda data och implementation

Encapsulation är en av grundpelarna inom OOP och handlar om att skydda data och hålla implementation gömd från omvärlden. Det innebär att vi skapar en "kapsel" runt vår klass som kontrollerar tillgången till dess innehåll.

### Access Modifiers (Åtkomstmodifierare)

Java har fyra nivåer av åtkomst:

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| `private` | Ja | Nej | Nej | Nej |
| `default` (ingen) | Ja | Ja | Nej | Nej |
| `protected` | Ja | Ja | Ja | Nej |
| `public` | Ja | Ja | Ja | Ja |

```java
public class Student {
    // Private - endast åtkomligt inom klassen
    private String name;
    private int studentId;
    
    // Default (ingen modifier) - åtkomligt inom samma package
    String department;
    
    // Protected - åtkomligt inom samma package och i subclasses
    protected int credits;
    
    // Public - åtkomligt överallt
    public boolean isActive;
}
```

### Information Hiding

Ett viktigt syfte med encapsulation är att dölja detaljer om implementation och exponera endast vad som behövs. Detta kallas ofta för "information hiding".

```java
public class BankAccount {
    // Private attributes - hidden from outside world
    private double balance;
    private String accountNumber;
    private List<Transaction> transactions;
    
    // Public methods - visible interface
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        balance += amount;
        transactions.add(new Transaction("deposit", amount));
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        
        if (amount > balance) {
            return false;
        }
        
        balance -= amount;
        transactions.add(new Transaction("withdrawal", amount));
        return true;
    }
}
```

I detta exempel:
- Vi döljer `balance`, `accountNumber` och `transactions` från direktåtkomst
- Vi exponerar metoder som `deposit()` och `withdraw()` som interagerar med datan på ett kontrollerat sätt
- Vi kan validera indata genom dessa metoder

### Fördelar med Encapsulation

1. **Dataskydd**: Förhindra att attribut ändras på ogiltiga sätt
2. **Flexibilitet**: Ändra implementationsdetaljer utan att påverka användare
3. **Kontroll**: Kontrollera hur data förändras
4. **Debugging**: Enklare att hitta och fixa fel

### Getters och Setters

Det vanligaste sättet att implementera encapsulation är genom getters och setters:

```java
public class Person {
    // Private attributes - encapsulated
    private String name;
    private int age;
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter methods with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age value");
        }
        this.age = age;
    }
}
```

### Nyttig vs Överdriven Encapsulation

Det är viktigt att balansera encapsulation. Att skapa getters och setters för varje attribut utan att lägga till någon logik är ofta onödigt. Överväg om ett attribut verkligen behöver en setter.

```java
// Kanske inte meningsfullt
public void setDayOfBirth(int day) { this.dayOfBirth = day; }
public void setMonthOfBirth(int month) { this.monthOfBirth = month; }
public void setYearOfBirth(int year) { this.yearOfBirth = year; }

// Mer meningsfullt
public void setBirthDate(int day, int month, int year) {
    // Validera hela datumet på en gång
    if (!isValidDate(day, month, year)) {
        throw new IllegalArgumentException("Invalid date");
    }
    this.dayOfBirth = day;
    this.monthOfBirth = month;
    this.yearOfBirth = year;
}
```

## Inheritance - Att bygga hierarkier av klasser

Inheritance (arv) är en av de mest kraftfulla koncepten inom OOP. Det låter en klass (subclass/child class) ärva egenskaper och beteenden från en annan klass (superclass/parent class).

### Grundläggande Inheritance

För att ärva från en klass använder vi nyckelordet `extends`:

```java
// Superclass/Parent class
public class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Subclass/Child class
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Anropar superklassens konstruktor
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " is barking");
    }
}
```

I detta exempel:
- `Dog` ärver alla attributes och methods från `Animal`
- `Dog` lägger till ett nytt attribut `breed` och en ny metod `bark()`
- Vi måste anropa `super(name, age)` för att initialisera de ärvda attributen

### Använda en subclass

```java
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Rex", 3, "German Shepherd");
        
        // Inherited methods
        dog.eat();    // Output: Rex is eating
        dog.sleep();  // Output: Rex is sleeping
        
        // Own method
        dog.bark();   // Output: Rex is barking
    }
}
```

### Method Overriding

Vi kan override (överskrida) metoder från superklassen för att ändra deras beteende:

```java
public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }
    
    // Override superclass method
    @Override
    public void eat() {
        System.out.println(name + " is eating slowly and elegantly");
    }
    
    public void meow() {
        System.out.println(name + " says meow");
    }
}
```

Viktigt att använda `@Override` annotation för att säkerställa att vi faktiskt överskrider en metod.

### Superklassreferenser

En referens av typen superclass kan peka på ett objekt av typen subclass (men inte tvärtom):

```java
Animal animal1 = new Dog("Rex", 3, "German Shepherd");
Animal animal2 = new Cat("Whiskers", 2);

animal1.eat();  // Anropar Dog's overridden version
animal2.eat();  // Anropar Cat's overridden version

// Fungerar inte:
// animal1.bark();  // Fel: Cannot find symbol
```

### Typer av Inheritance

1. **Single Inheritance**: En klass ärver från en klass (som i exemplen ovan)
2. **Multilevel Inheritance**: Kedja av arv (A → B → C)
3. **Hierarchical Inheritance**: Flera klasser ärver från samma klass

Java stöder inte multiple inheritance för klasser (en klass kan inte ärva från flera klasser). Detta är ett medvetet designval för att undvika problem som "diamond problem".

```java
// Multilevel inheritance
public class Animal { ... }
public class Mammal extends Animal { ... }
public class Dog extends Mammal { ... }

// Hierarchical inheritance
public class Animal { ... }
public class Dog extends Animal { ... }
public class Cat extends Animal { ... }
```

### The Object Class

Alla klasser i Java ärver implicit från `Object`-klassen. Det är roten i Java's klasshierarki.

```java
public class MyClass {
    // Implicit: extends Object
}
```

Detta ger alla klasser tillgång till grundläggande metoder som:
- `toString()`
- `equals()`
- `hashCode()`
- `getClass()`

### Konstruktorer och Inheritance

När en subclass skapas, anropas först superklassens konstruktor. Om superklassen inte har en no-arg (parameterless) konstruktor, måste subklassen explicit anropa en av superklassens konstruktorer med `super()`.

```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
}

public class Student extends Person {
    private int studentId;
    
    public Student(String name, int studentId) {
        super(name);  // Måste anropa superklassens konstruktor först
        this.studentId = studentId;
    }
    
    // Fel: Om detta saknas kommer kompileringen misslyckas
    // eftersom Person inte har en no-arg konstruktor
    // public Student() { }  
}
```

#### Djupare förståelse av konstruktoranrop i Inheritance

Här är fler detaljer och exempel på hur `super()` fungerar i konstruktorkedjor:

##### 1. Implicit vs Explicit super() anrop

```java
public class Animal {
    // Default no-arg constructor (skapas implicit om ingen annan konstruktor finns)
    public Animal() {
        System.out.println("Animal constructor called");
    }
}

public class Dog extends Animal {
    public Dog() {
        // Implicit super() anrop här om inget explicit anges
        System.out.println("Dog constructor called");
    }
}

// När vi skapar: new Dog();
// Output:
// Animal constructor called
// Dog constructor called
```

##### 2. När superklassen inte har en no-arg konstruktor

```java
public class Animal {
    private String species;

    // Endast en konstruktor med parameter
    public Animal(String species) {
        this.species = species;
    }
}

public class Dog extends Animal {
    private String breed;
    
    // Fungerar - explicit anrop till superklassens konstruktor
    public Dog(String breed) {
        super("Canine");  // Måste anges explicit
        this.breed = breed;
    }
    
    // Fungerar också - skickar parametern vidare
    public Dog(String species, String breed) {
        super(species);
        this.breed = breed;
    }
    
    // Kompileringsfel - ingen super() anrop till Animal(String)
    /*
    public Dog() {
        this.breed = "Unknown";  // Error: implicit super() saknas
    }
    */
}
```

##### 3. När superklassen har både no-arg och parametriserade konstruktorer

```java
public class Animal {
    private String species;
    
    // No-arg konstruktor
    public Animal() {
        this.species = "Unknown";
    }
    
    // Parametriserad konstruktor
    public Animal(String species) {
        this.species = species;
    }
}

public class Dog extends Animal {
    private String breed;
    
    // Fungerar - implicit super() anropar Animal()
    public Dog(String breed) {
        // super() anropas implicit här
        this.breed = breed;
    }
    
    // Explicit val av superklassens konstruktor
    public Dog(String species, String breed) {
        super(species);  // Anropar Animal(String)
        this.breed = breed;
    }
    
    // Explicit anrop av no-arg konstruktor
    public Dog() {
        super();  // Tydligare, men inte nödvändigt (skulle anropas implicit)
        this.breed = "Mixed";
    }
}
```

##### 4. Konstruktorkedja i flernivå-arv

```java
public class Animal {
    public Animal() {
        System.out.println("Animal created");
    }
}

public class Mammal extends Animal {
    public Mammal() {
        System.out.println("Mammal created");
    }
}

public class Dog extends Mammal {
    public Dog() {
        System.out.println("Dog created");
    }
}

// När vi skapar: new Dog();
// Output:
// Animal created
// Mammal created
// Dog created
```

##### 5. Anropa en annan konstruktor i samma klass med `this()`

Detta är inte direkt relaterat till `super()`, men används ofta tillsammans med det:

```java
public class Dog extends Animal {
    private String breed;
    private int age;
    
    // Primär konstruktor
    public Dog(String species, String breed, int age) {
        super(species);
        this.breed = breed;
        this.age = age;
    }
    
    // Kedjar till primär konstruktor
    public Dog(String breed, int age) {
        this("Canine", breed, age);  // Anropar Dog(String, String, int)
    }
    
    // Annan konstruktorkedja
    public Dog(String breed) {
        this(breed, 0);  // Anropar Dog(String, int)
    }
    
    // Obs! Du kan inte använda både this() och super() i samma konstruktor
    // eftersom båda måste vara det första uttalandet
}
```

#### Vanliga misstag och lösningar

##### Misstag 1: Glömma super() när det behövs

```java
public class Parent {
    public Parent(int value) {
        // Någon kod här
    }
}

public class Child extends Parent {
    // Kompileringsfel - saknar super(int)
    public Child() {
        // Implicit super() fungerar inte eftersom Parent 
        // inte har någon no-arg konstruktor
    }
}
```

**Lösning:**
```java
public class Child extends Parent {
    public Child() {
        super(0);  // Explicit anrop med standardvärde
    }
}
```

##### Misstag 2: Lägga super() på fel plats

```java
public class Child extends Parent {
    public Child() {
        System.out.println("Child constructor start");
        super(10);  // Kompileringsfel: super() måste vara första uttalandet
    }
}
```

**Lösning:**
```java
public class Child extends Parent {
    public Child() {
        super(10);  // Korrekt position - måste vara först
        System.out.println("Child constructor start");
    }
}
```

##### Misstag 3: Försöka använda både this() och super()

```java
public class Child extends Parent {
    public Child(int value) {
        super(value);
    }
    
    // Kompileringsfel
    public Child() {
        super(0);  // Kan inte ha både super() och this()
        this(10);  // i samma konstruktor
    }
}
```

**Lösning:**
```java
public class Child extends Parent {
    public Child(int value) {
        super(value);
    }
    
    public Child() {
        this(10);  // Anropar andra konstruktorn som i sin tur anropar super()
    }
}
```

#### Best Practices för konstruktorer i arv

1. **Designa tydliga konstruktorkedjor**
   - Ha en "primär" konstruktor som tar alla parametrar
   - Låt enklare konstruktorer kedja till den primära
   - Detta minskar kodduplicering och ger en logisk struktur

2. **Använd meningsfulla standardvärden**
   - När du anropar super() med ett standardvärde, se till att det är meningsfullt
   ```java
   super("Unknown");  // Tydligt standardvärde för en saknad sträng
   ```

3. **Dokumentera konstruktorer**
   - Eftersom konstruktorkedjor kan bli komplexa, dokumentera varje konstruktor
   ```java
   /**
    * Skapar en hund med standardart (Canine) och den givna rasen.
    * @param breed Hundens ras
    */
   public Dog(String breed) {
       super("Canine");
       this.breed = breed;
   }
   ```

4. **Överlastningsmönster**
   - Om du har flera konstruktorer, följ ett konsekvent mönster
   - Tänk på vilka parametrar som är obligatoriska vs. valfria
   ```java
   // Primär - alla parametrar
   public Person(String name, int age, String address) {...}
   
   // Sekundär - utelämna valfria parametrar
   public Person(String name, int age) {
       this(name, age, "Unknown");
   }
   
   public Person(String name) {
       this(name, 0);
   }
   ```

5. **Undvik för många konstruktorer**
   - Om du har många olika konstruktorer kan det indikera en dålig design
   - Överväg att använda Builder pattern för mer komplexa objekt
   ```java
   Person person = new Person.Builder()
       .name("John")
       .age(30)
       .address("123 Main St")
       .build();
   ```

6. **Validera indata i konstruktorn**
   - Kontrollera parametrar tidigt för att säkerställa objektets integritet
   ```java
   public Person(String name) {
       if (name == null || name.trim().isEmpty()) {
           throw new IllegalArgumentException("Name cannot be empty");
       }
       super("Person");
       this.name = name;
   }
   ```

### The `final` Keyword

`final` kan användas för att förhindra inheritance:

- **final class**: Kan inte ärvas
- **final method**: Kan inte överridas

```java
// Denna klass kan inte ärvas
public final class ImmutableString {
    private final String value;
    
    public ImmutableString(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}

public class Animal {
    // Denna metod kan inte overridas i subclasses
    public final void breathe() {
        System.out.println("Breathing...");
    }
}
```

### Protected vs Private

Ett vanligt misstag är att göra alla attributes `private` och sen försöka komma åt dem direkt i subklasser:

```java
public class Animal {
    private String name;  // Private - inte åtkomlig i subklasser
    
    public Animal(String name) {
        this.name = name;
    }
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    public void bark() {
        // Error: name has private access in Animal
        System.out.println(name + " is barking");
    }
}
```

Lösningar:
1. Ändra attributet till `protected` (ger åtkomst i subklasser)
2. Använd getters och setters i subklassen

### "Is-A" vs "Has-A" Relation

Inheritance bör användas för att modellera "is-a"-relationer, medan composition (ett objekt har referenser till andra objekt) bör användas för "has-a"-relationer.

- **Is-A**: Dog is an Animal (inheritance)
- **Has-A**: Car has an Engine (composition)

```java
// Is-A relation (inheritance)
public class Animal { ... }
public class Dog extends Animal { ... }

// Has-A relation (composition)
public class Engine { ... }
public class Car {
    private Engine engine;  // Car has-a Engine
    
    public Car() {
        this.engine = new Engine();
    }
}
```

Att välja mellan inheritance och composition kan ibland vara svårt, men en bra tumregel är:
- Använd inheritance när B verkligen är en speciell typ av A
- Använd composition när B bara behöver någon av A's funktionaliteter

## Praktiska övningar

### Övning 1: Bankapplikation med encapsulation

Skapa en klass `BankAccount` med följande:
- Private attributes: `accountNumber`, `balance`, `ownerName`
- Getters för alla attributes
- Setters med validering för `ownerName`
- Metoder för `deposit()` och `withdraw()` med lämpliga kontroller
- En `transfer()` metod för att överföra pengar mellan konton

### Övning 2: Inheritance-hierarki för fordon

Skapa en hierarki av fordonsklasser:
1. En basklass `Vehicle` med attribut som `brand`, `model`, `year` och metoder som `startEngine()` och `stopEngine()`
2. Subklasser för `Car`, `Motorcycle` och `Truck` som ärver från `Vehicle`
3. Lägg till unika attribut och metoder i varje subklass (t.ex. `numberOfDoors` i `Car`)
4. Override metoden `startEngine()` i varje subklass för att ge en specifik implementation

### Övning 3: Kombinera encapsulation och inheritance

Modifiera övning 2:
1. Gör alla attributes i `Vehicle` private eller protected
2. Implementera lämpliga getters och setters
3. Lägg till validation i setters
4. Implementera toString() i alla klasser för att visa fordonsinformation

## Sammanfattning

### Encapsulation:
- Dölj implementation och exponera enbart vad som behövs
- Använd access modifiers (`private`, `protected`, `public`) för att kontrollera åtkomst
- Implementera getters och setters med validering

### Inheritance:
- Låter en klass ärva egenskaper och beteenden från en annan
- Används för "is-a"-relationer
- Möjliggör code reuse och hierarkisk organisation av klasser

Dessa koncept är fundamentala för objektorienterad design och används ofta tillsammans för att skapa robusta, återanvändbara och underhållbara kodbaser.
