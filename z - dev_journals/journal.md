📓 EarthHub Development Journal

Date: March 24, 2026
Project Phase: Initial Infrastructure & Persistence Layer
Day: 01
🚀 Accomplishments

    Project Initialization: Successfully bootstrapped the EarthHub project structure using Java and Spring Boot. Defined core dependencies for the initial build.

    Database Integration: Configured the application to communicate with a PostgreSQL database. Verified the connection is active and stable.

    Entity Mapping: Created the first database-backed Java file: the AppUser entity. This mapped the class to the app_users table in PostgreSQL.

    Git Workflow: Established a clean commit history for the foundation of the project.

🧠 Key Learnings & Insights
1. Hibernate & JPA Deep Dive

   Lifecycle Awareness: Studied the Hibernate Entity Lifecycle. Understanding the transition from Transient (new object) to Persistent (managed by session) is vital for ensuring data is actually saved when expected.

   Annotation Mastery: Implemented specific JPA annotations to define the schema:

        @Id & @GeneratedValue: For primary key management.

        @Column & @Table: For mapping Java names to SQL names.

        @Enumerated: To handle type-safe Enums in the database.

        @CreationTimestamp: To automate "Created At" metadata.

2. Lombok Best Practices for JPA

   The @Data Trap: Learned that using @Data on JPA entities can lead to performance issues and recursive loops (StackOverflowErrors) because it overrides equals(), hashCode(), and toString() in ways that can trigger lazy-loading unexpectedly.

   Selective Boilerplate: Switched to using @Getter and @Setter specifically to keep the entities lightweight and safe.

3. Data Type Precision

   Wrapper vs. Primitive: Identified why Long is preferred over long for IDs. A null value in a Long clearly indicates a new, unsaved entity, whereas a 0 in a primitive long can be ambiguous to the Hibernate engine.

### Personal
I was very frustrated getting postgres to work that I had to reinstall. Happily it works fine now.
Still unsure about the project direction, I'll figure it out as I go.