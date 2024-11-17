package SupervisorWork;

import AdditionalMethods.SpecialFeatures;
import Colors.ColoredText;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class SentimentAnalysis {
    private int TotalSentimentScore;

    private static final Set<String> positiveWords = new HashSet<>(Arrays.asList(
            "accommodating", "affordable", "amazing", "appreciated","well" ,"super","attentive", "beautiful", "clean",
            "comfortable", "convenient", "cozy", "courteous", "enjoyable", "excellent", "exceptional",
            "friendly", "good", "great", "happy", "helpful", "homely", "hygienic", "impressive",
            "incredible", "kind", "lovely", "memorable", "neat", "outstanding", "peaceful",
            "pleasant", "positive", "professional", "prompt", "quiet", "reliable", "relaxing",
            "respectful", "safe", "satisfied", "secure", "spacious", "supportive", "sweet",
            "tidy", "trustworthy", "welcoming", "wonderful", "well-maintained", "organized",
            "calm", "beautifully-kept", "value", "serene", "fantastic", "affordable", "sociable",
            "efficient", "attentive", "bright", "comfortable beds", "accessible", "nearby",
            "functional", "modern", "luxurious", "well-equipped", "refined", "amazing service",
            "peaceful surroundings", "pleasant ambiance", "outdoor space", "clean linens", "friendly staff",
            "beautifully designed", "excellent hospitality", "relaxed atmosphere", "awesome", "personalized",
            "well-maintained rooms", "well-kept", "supportive staff", "comfortable surroundings", "unique",
            "tranquil", "sleek", "family-friendly", "stylish", "polite", "charming", "appealing", "excellent location",
            "convenient amenities", "calm environment", "top-notch", "budget-friendly", "bright rooms", "large rooms",
            "fantastic experience", "new", "classy", "fresh", "reliable services", "inspiring", "clean bathrooms",
            "welcoming atmosphere", "clean kitchen", "clean environment", "relaxing place", "peaceful surroundings",
            "well-managed", "helpful services", "pleasant reception", "fresh air", "positive feedback", "chilled",
            "efficient staff", "spacious rooms", "efficient room service", "friendly reception", "well-lit",
            "tranquil space", "reliable wifi", "comfortable lounge", "well-stocked", "stylish interiors",
            "quiet rooms", "excellent maintenance", "easy check-in", "comfortable seating", "affordable prices",
            "satisfactory", "highly recommended", "high-quality", "modern decor", "clean towels", "convenient location",
            "secure environment", "vibrant", "luxurious facilities", "neat surroundings", "professional staff",
            "accommodation options", "fresh linens", "good communication", "trusted", "enjoyable atmosphere",
            "customized services", "helpful managers", "secure access", "modern amenities", "clean surroundings",
            "clean furniture", "helpful guidance", "large lounge", "functional kitchen", "quality food", "green space",
            "comfy beds", "scenic views", "well-maintained property", "comfortable experience", "pleasant environment",
            "easy access", "calming", "spacious areas", "comfortable temperature", "peaceful nights", "private space",
            "great value", "lovely stay", "homely environment", "reliable services", "peaceful atmosphere",
            "newly renovated", "stylish design", "clean floor", "cool ambiance", "warm hospitality",
            "good value for money", "budget-friendly accommodations", "pleasant decor", "all-inclusive", "chilled vibes",
            "organized services", "amazing customer service", "responsive staff", "unique atmosphere", "well-equipped rooms",
            "natural light", "comfortable bedding", "quiet neighborhood", "well-located", "great customer service",
            "good amenities", "convenient parking", "peaceful location", "excellent room service", "clean floors",
            "affordable rates", "ideal location", "clean windows", "pleasant stay", "value for money", "room comfort",
            "superb service", "high-end amenities", "wonderful food", "well-managed property", "comfortable ambiance",
            "affordable rates", "cozy lounge", "warm service", "beautiful location"
    ));


    private static final Set<String> negativeWords = new HashSet<>(Arrays.asList(
            "annoying", "bad", "broken", "careless", "cheap", "cluttered", "cramped", "dangerous",
            "dirty", "disappointing", "disorganized", "dismal","cleanness", "disturbing", "drab", "filthy",
            "frustrating", "horrible", "inattentive", "inconvenient", "indifferent", "insufficient",
            "lacking", "loud", "messy", "miserable", "moldy", "negative", "neglected", "noisy",
            "overcrowded", "poor", "rude", "rusty", "scary", "shabby", "smelly", "stained",
            "stressful", "terrible", "uncomfortable", "unfriendly", "unhygienic", "unpleasant",
            "unsafe", "unsatisfactory", "unstable", "untrustworthy", "unwelcoming", "worn-out",
            "worst", "expensive", "cold", "difficult", "disturbing", "misleading", "unbearable",
            "sloppy", "broken beds", "insufficient facilities", "unresponsive", "slow",
            "irresponsible", "defective", "unreliable", "crowded", "impractical", "unhygienic",
            "unavailable", "worn-out furniture", "unhelpful", "unpleasant service", "unaccommodating",
            "unsanitary", "wasteful", "poor service", "inconsiderate","ugly","notgood", "unreliable wifi", "poorly maintained",
            "rude staff", "unsafe environment", "damaged", "too small", "outdated", "unreliable access",
            "disorganized staff", "unpleasant odor", "dirty room", "unhygienic conditions", "broken amenities",
            "noisy environment", "rude reception", "uncaring", "disruptive", "uncomfortable beds", "unfriendly staff",
            "filthy bathroom", "noisy neighbors", "unresponsive management", "lack of privacy", "low-quality",
            "unhelpful staff", "unprofessional", "unfit", "unmanaged", "unsuitable", "unsupportive",
            "misleading information", "inefficient", "disconnected", "awful experience", "disturbing noise",
            "unsatisfying", "poor lighting", "poor ventilation", "smelly bathroom", "poor maintenance",
            "uneven service", "understaffed", "inadequate", "unpleasant environment", "shoddy", "poor hygiene",
            "highly disappointing", "miscommunication", "dirty towels", "unsatisfactory food", "low-standard",
            "rundown", "noisy air conditioning", "shoddy furniture", "uncomfortable seating", "unfriendly reception",
            "poorly organized", "unaccommodating manager", "lacking amenities", "limited facilities", "dirty carpets",
            "broken windows", "unsafe neighborhood", "too hot", "too cold", "too far", "noisy plumbing",
            "noisy air conditioning", "incompetent staff", "insensitive staff", "cheap materials", "lack of security",
            "poorly lit", "dismal surroundings", "expensive for quality", "inflexible", "unacceptable",
            "poor customer service", "unwelcoming space", "unmaintained", "terrible location", "unreliable heating",
            "poor bedding", "unhygienic food", "inconsistent", "dirty floors", "unhelpful service", "dull",
            "poor communication", "unorganized check-in", "unsuitable for long stay", "frustrating check-in",
            "inconsiderate staff", "unsanitary conditions", "high noise levels", "unattractive decor",
            "unacceptable service", "wasteful energy", "unsafe parking", "poor quality food", "irritating",
            "dangerous facilities", "unclean environment", "noisy surroundings", "uncomfortable temperature",
            "unfriendly atmosphere", "no hot water", "uncomfortable pillows", "unresponsive management",
            "slow service", "unreliable staff", "unnecessary charges", "noisy hallways", "unresponsive staff",
            "noisy air conditioning", "limited comfort", "stiff beds", "disgusting", "frustrating wait",
            "unmanaged", "stale air", "low water pressure", "expensive for what you get", "unpleasant check-in",
            "uncooperative staff", "unhelpful front desk", "unsupportive management"
    ));


    public SentimentAnalysis(){
        TotalSentimentScore=0;
    }


    public String analyzeSentiment(String text) {
        // Tokenize text into words
        String[] words = text.toLowerCase().split("\\s+");

        int sentimentScore = 0;

        // Calculate sentiment score
        for (String word : words) {
            if (positiveWords.contains(word)) {
                sentimentScore++;
                TotalSentimentScore++;
            } else if (negativeWords.contains(word)) {
                sentimentScore--;
                TotalSentimentScore--;
            }
        }

        // Determine sentiment
        if (sentimentScore > 0) {
            return "Positive";
        } else if (sentimentScore < 0) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }

    public ArrayList<String> CheckFile(String StartStr) {
        // Replace "path/to/your/directory" with the actual directory path
        File directory = new File("DataBase/FeedBack/FeedBackList");
        ArrayList<String> FbList = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            // List all files in the directory
            File[] files = directory.listFiles();

            if (files != null) {
//                System.out.println("Files starting with "+StartStr+" in directory " + directory.getPath() + ":");
                for (File file : files) {
                    // Check if it's a file and if the name starts with "S"
                    if (file.isFile() && file.getName().startsWith(StartStr)) {
//                        System.out.println(file.getName());
                        System.out.println(" ");
                        FbList.add(file.getName());
                    }
                }

            } else {
                System.out.println("The directory is empty or could not be read.");
            }
        } else {
            System.out.println("The specified path is not a directory or does not exist.");
        }
        return FbList;
    }

    public void getSentimentAnalysis(String FbId) {
        // Example sentences to test the program
        int totline=0;
        ArrayList<String> FbList = new ArrayList<>();
        FbList=CheckFile(FbId);
        for(int i=0;i<FbList.size();i++){
            String lines[]= SpecialFeatures.ReadTxtFile("DataBase/FeedBack/FeedBackList/"+FbList.get(i));
            for(int j=0;j<lines.length;j++){
                System.out.println(""+(++totline)+". "+lines[j]+" : "+analyzeSentiment(lines[j]));

            }

        }
        if(TotalSentimentScore<0)
            System.out.println(ColoredText.BOLD_RED+"\n\n Overall Analysis : NEGATIVE \n"+ ColoredText.RESET);
        else if (TotalSentimentScore>0) {
            System.out.println(ColoredText.BOLD_GREEN+"\n\n Overall Analysis : POSITIVE \n"+ ColoredText.RESET);
        }
        else{
            System.out.println(ColoredText.BOLD_YELLOW+"\nOverall Analysis : NEUTRAL \n"+ ColoredText.RESET);
        }
    }
}
