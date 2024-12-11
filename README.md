# Food Storage
My a Java-application is my submission to the grading format that replaces the exam.

<i>The FoodStorage project aims to make the user more aware of their
food storage, as well as being more economic and more environment friendly.</i>

## Start application
<b>Run the Main-class</b> then you will see different options that you may follow.

## Features
- Access the food storage
- Access recipes
- Exit

### Access the food storage
You are presented with different actions:
1. Add grocery (`name`, `quantity`, `unit`, `price`, `expiration date`)
2. Remove grocery (`name`, `quantity`, `unit`)
3. View all stored groceries
4. Search grocery (`name`)
5. Search expiration date for groceries (`date`)
6. View expired groceries
7. Get total value of food storage
8. Exit to main menu

### Access recipes
You are presented with different actions:
1. Add recipe (`name`, `description`, `ingredients`, `portions`, `procedure`)
   - ingredients (`name`, `quantity`, `unit`, `allergic`)
2. Remove recipe (`name`)
3. View all recipes
4. Search for a recipe (`name`)
5. View suggested recipes
6. Use a recipe (`name`, `portions`)\*
7. Exit to main menu

\* Be aware that if you use more portions on a recipe than what you have,
the program assumes you somehow have gotten hand on more ingredients, and removes thereafter.

### Exit
When you are done updating the storage and gotten idea of what you want to eat,
you might want to exit the application. That is easy. Just enter `0` and you exit a layer.
You are never more than 2 layers deep (except when filling info), so `0` followed by `0`
will always get you out.
