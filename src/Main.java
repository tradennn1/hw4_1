import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {270, 270, 350, 400, 250, 200, 250, 270};
    public static int[] heroesDamage = {22, 24, 20, 0, 15, 44, 28, 30};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;
    public static int hill = 30;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefence);


    }

    public static void round() {
        round_number++;
        chooseDefence();
        bossHits();
        heroesHit();
        berserkShoot();
        thor();
        printStatistics();
        medicHill();
        lucky();
        golemDefence();
    }

    private static void lucky() {
        Random random = new Random();
        int randomLucky = random.nextInt(4) + 1;
        switch (randomLucky) {
            case 1:
                heroesHealth[6] = heroesHealth[6] + bossDamage;
                System.out.println("Лаки уклонился");
            case 2:

            case 3:

            case 4:
        }
    }

    private static void thor() {
        Random random = new Random();
        boolean thor = random.nextBoolean();
        if (thor) {
            bossDamage = 0;
            System.out.println("Босс оглушен");
        } else {
            bossDamage = 50;
        }
    }

    private static void berserkShoot() {
        Random random = new Random();
        int randomDamage = random.nextInt(15) + 1;
        int randomC = random.nextInt(3) + 1;
        if (heroesHealth[4] > 0 && bossHealth > 0) {
            switch (randomC) {
                case 1:
                    heroesDamage[4] = (heroesDamage[4] + bossDamage) - randomDamage;
                    System.out.println("Крититический урон Берсерка");
                    System.out.println("Потери при увеличении урона Берсерка " + randomDamage);
                    break;
                case 2:
                    bossDamage = 50;
                    break;
                case 3:
                    bossDamage = 50;
                    break;

            }
        }
    }

    private static void golemDefence() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7] > 0 && heroesHealth[i] > 0 && heroesHealth[7] != heroesHealth[i]) {
                heroesHealth[i] += bossHealth / 5;
                heroesHealth[7] -= bossDamage / 5;
            }
        }
    }


    private static void medicHill() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int randomHill = random.nextInt(40);
            int randomHero = random.nextInt(2);
            if (heroesHealth[i] > 0 && heroesHealth[3] > 0 && heroesHealth[i] < 100) {
                heroesHealth[randomHero] = heroesHealth[randomHero] + randomHill;
                heroesHealth[i] += 40;
                System.out.println("Медик вылечил " + randomHill);

            }
        }

    }
    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            /*if (bossHealth <= 0) {
                break;
            }*/
            int damage = heroesDamage[i];
            if (heroesAttackType[i] == bossDefence) {
                Random random = new Random();
                //int coeff = random.nextInt(10) + 2; // 2,3,4,5,6,7,8,9,10,11
                int coeff = random.nextInt(11); // 0,1,2,3,4,5,6,7,8,9,10
                damage = heroesDamage[i] * coeff;
                System.out.println("Critical damage: " + heroesDamage[i]
                        + " * " + coeff + " = " + damage);
            }
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + round_number + "  _________________");
        System.out.println("Boss health: " + bossHealth + "; damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]
                    + "; damage: " + heroesDamage[i]);
        }
    }
}
