//Edwin
import java.util.Scanner;

public class Interactable extends Item{

    private String name;
    private String description;
    private int quantity;
    private int roomNumber;

    private String type;

    private String pin;
    private int attempts;
    private boolean hasUsedMaxAttempts = false;

    public Interactable(String name, String description, int roomNumber){
        this.name = name;
        this.description = description;
        this.quantity = 1;
        this.roomNumber = roomNumber;
        this.type = "Interactable";
        this.pin = "6057";
        this.attempts = 5;
    }


    //Using the one interactable item will be used to end the game
    //This method will print out to console because of it's behavior
    public void use(Interactable item){
        while (hasUsedMaxAttempts = false){

            if (attempts  == 0){
                hasUsedMaxAttempts = true;
                System.out.println("You have used all your attempts");
                break;
            }
            Scanner input = new Scanner(System.in);
            System.out.println("An old satellite phone. You try dialing some numbers, but the call doesn’t go through." +
                    " It is asking for the pin number. Enter 0000 to put the phone away");
            String attempt = input.nextLine();

            if (attempt.equalsIgnoreCase("0000")){
                break;
            }
            else if (attempt.equals(pin)){
                //Trigger ending
                System.out.println("’Pin accepted.’\n" +
                        "\n" +
                        "Those words brighten up your entire life. The phone is waiting for you to dial the number of the party you wish to reach.\n" +
                        "\n" +
                        "You decide that calling 9-1-1 would be your best shot of reaching someone that could help you in this situation.\n" +
                        "\n" +
                        "It rings. It rings again. It rings again.\n" +
                        "\n" +
                        "‘9-1-1 what is your emergency?’\n" +
                        "\n" +
                        "Oh, thank heavens.\n" +
                        "\n" +
                        "‘H-hi, my name is [redacted], and I’ve been stranded on a deserted island with no knowledge of how I ended up here. The\n" +
                        "\n" +
                        "last thing I remember was working my shift at [redacted] and losing consciousness.’\n" +
                        "\n" +
                        "‘Okay, do you have any idea of where you are located right now?’\n" +
                        "\n" +
                        "‘I’m not entirely sure. This island looks deserted, but it’s got so much wildlife on it. There’s a mountain with a great view, a volcano, a lighthouse, a sky garden... It’s a bit of a shame nobody else is here, honestly. There’s also been a shipwreck on the edge of the island. I’m calling from a satellite phone I found in the sick bay.’\n" +
                        "\n" +
                        "The officer pauses for a moment. ‘...okay sir, we’re aware of a ship that’s been missing for a few weeks now. We’ll send helicopters out to look for an island with a ship and all these landmarks you’ve described. Please hang tight for as long as possible.’\n" +
                        "\n" +
                        "You detected a hint of distrust in the officer’s voice, but you’re not going to accuse the person who’s about to save your life of anything.\n" +
                        "\n" +
                        "‘Alright, thank you so much. I don’t know what I would’ve done without you.’\n" +
                        "\n" +
                        "What feels like a couple of hours has passed. You’ve made yourself as comfortable as possible on the ship while keeping tabs on the outside to check if any helicopters have flown by. You’ve also carved a large SOS in the sand just outside of the ship to hopefully indicate to anyone flying by of your location.\n" +
                        "\n" +
                        "A slight buzzing of helicopter rotors fills your ear. You rush out of the sickbay and out of the ship and scream at the top of your lungs. The helicopter pauses for a second in the air, then heads right for your location.\n" +
                        "\n" +
                        "The chopper lands near your location. You run up to the pilot and give them your most sincere thanks. They’re not too keen on conversating right now. You’re instructed to head into the back seat and answer questions that they have for you on the ride home. You are, of course, given some food and water before you set for the skies.\n" +
                        "\n" +
                        "It feels like an interrogation. What could you have possibly done wrong? You're asked various questions about names you’ve never heard in your life and what you were doing last month. You answer as honestly as possible, but you never shake the feeling of disco\n" +
                        "\n" +
                        "It’s been a year since the incident, and you feel more endangered at home than you felt on the island. The police suspect you’re the one responsible for the murder of the entire crew of the ship Mordor. It’s folklore at this point that you’re the one who caused the death of an entire crew. You’re not allowed to go 100 miles past the city limits. Your family understands your situation and knows the truth, but the long arm of the law may never know.\n" +
                        "\n" +
                        "The end.");
                System.exit(0);
            }
            else {
                System.out.println("Wrong pin.");
                attempts--;
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getPin() {
        return pin;
    }

    public int getAttempts() {
        return attempts;
    }

    public boolean isHasUsedMaxAttempts() {
        return hasUsedMaxAttempts;
    }
}
