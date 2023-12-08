import java.util.HashMap;

public class URLShortener {
    private HashMap<String, String> shortToLongMap;
    private HashMap<String, String> longToShortMap;
    private static final String DOMAIN = "https://short.link/";

    public URLShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    // Method to generate a short link and store the mapping
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return DOMAIN + longToShortMap.get(longURL);
        }

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortURL = new StringBuilder();
        // Generate a unique short link
        for (int i = 0; i < 7; i++) {
            int index = (int) (Math.random() * characters.length());
            shortURL.append(characters.charAt(index));
        }

        shortToLongMap.put(shortURL.toString(), longURL);
        longToShortMap.put(longURL, shortURL.toString());

        return DOMAIN + shortURL.toString();
    }

    // Method to retrieve long URL from short link
    public String getLongURL(String shortURL) {
        String shortKey = shortURL.replace(DOMAIN, "");
        return shortToLongMap.getOrDefault(shortKey, "Short link does not exist.");
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();

        // Example usage
        String longURL = "https://www.example.com";
        String shortLink = urlShortener.shortenURL(longURL);
        System.out.println("Shortened URL: " + shortLink);

        String retrievedURL = urlShortener.getLongURL(shortLink);
        System.out.println("Retrieved URL: " + retrievedURL);
    }
}
