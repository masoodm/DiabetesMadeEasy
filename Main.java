package com.company;

import java.util.Scanner;

public class Main {
/*If you are sedentary (little or no exercise) : Calorie-Calculation = BMR x 1.2
If you are lightly active (light exercise/sports 1-3 days/week) : Calorie-Calculation = BMR x 1.375
If you are moderatetely active (moderate exercise/sports 3-5 days/week) : Calorie-Calculation = BMR x 1.55
If you are very active (hard exercise/sports 6-7 days a week) : Calorie-Calculation = BMR x 1.725
If you are extra active (very hard exercise/sports & physical job or 2x training) : Calorie-Calculation = BMR x 1.9*/

/*BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)*/



    public static void BMRcalculator (clientInfo person) {

        if (person.gender == "Male")
            person.BMR = 10 * weightToKG(person) + 6.25 * heightToCM(person) - 5 * person.age + 5;
        if (person.gender == "Female")
            person.BMR = 10 * weightToKG(person) + 6.25 * heightToCM(person) - 5 * person.age - 161;


    }

    public static double heightToCM (clientInfo person) {
        double cm;
        cm = (person.heightFeet * 12 + person.heightInches) * 2.54;
        return cm;
    }

    public static double weightToKG(clientInfo person) {
        double kg = person.weight * 0.453592;
        return kg;
    }


    public static void infoGatherer (clientInfo person) {
        int checker = 0;
        Scanner reader = new Scanner(System.in);

        System.out.println("Hello, please enter your name:");
        person.name = reader.next();

        System.out.println("Please choose which gender you are:");
        while (checker == 0){
            checker = 1;
            System.out.println("1. Male");
            System.out.println("2. Female");
            int number = reader.nextInt();
            if (number == 1)
                person.gender = "Male";
            else if (number == 2)
                person.gender = "Female";
            else {
                System.out.println("Please enter 1 or 2");
                checker = 0;
            }
        }

        System.out.println("What is your weight in pounds?");
        person.weight = reader.nextInt();

        System.out.println("What is your height?");
        System.out.print("Feet: ");
        person.heightFeet = reader.nextInt();
        System.out.print("Inches: ");
        person.heightInches = reader.nextInt();

        System.out.println("What is your age?");
        person.age = reader.nextInt();

    }

    public static void activityLevel(clientInfo person) {
        Scanner reader = new Scanner(System.in);
        int check = 0;
        System.out.println("What is your activity level?");

        while (check == 0) {
            check = 1;
            System.out.println("1. Sedentary (little or no exercise)");
            System.out.println("2. Lightly active (light exercise/sports 1-3 days/week)");
            System.out.println("3. Moderatetely active (moderate exercise/sports 3-5 days/week)");
            System.out.println("4. Very active (hard exercise/sports 6-7 days a week)");
            System.out.println("5. Extra active (very hard exercise/sports & physical job or 2x training)");
            int number = reader.nextInt();
            if (number == 1) {
                person.activityLevel = "Sedentary";
                person.totalCalories = person.BMR * 1.2;

            }
            else if (number == 2) {
                person.activityLevel = "Lightly active";
                person.totalCalories = person.BMR * 1.375;

            }
            else if (number == 3) {
                person.activityLevel = "Moderately active";
                person.totalCalories = person.BMR * 1.55;
            }
            else if (number == 4) {
                person.activityLevel = "Very active";
                person.totalCalories = person.BMR * 1.725;
            }
            else if (number == 5) {
                person.activityLevel = "Extra active";
                person.totalCalories = person.BMR * 1.9;
            }
            else
                check = 0;

        }


    }

    public static void calculator(clientInfo person) {
        BMRcalculator(person);
        activityLevel(person);

    }

    public static void carbCalculator (clientInfo person) {
        Scanner reader = new Scanner(System.in);

        System.out.println("How many units is your basal?");
        person.dailyBasal = reader.nextInt();

        System.out.println("How many grams do you eat per units of insulin?");
        person.carbRatio = reader.nextInt();

        double calsOfCarb = person.totalCalories * .65;
        person.dailyCarb = calsOfCarb / 4;

        person.dailyInsulin = person.dailyCarb / person.carbRatio + person.dailyBasal;
        person.dailyInsulin = Math.round(person.dailyInsulin);

        person.dailyCarb = Math.round(person.dailyCarb);
    }

    public static void main(String[] args) {
        clientInfo person = new clientInfo();

        infoGatherer(person);
        calculator(person);
        carbCalculator(person);

        System.out.println("Alright " + person.name + ", we have your calculations. \nThe total units of insulin you should take a day to maintain your weight is " + person.dailyInsulin + " units per day and " + person.dailyCarb + " grams of carb per day!");


    }

}
