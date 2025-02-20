import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


abstract class Document {
    protected String extension;
    protected String encryption;
    
    public abstract void setExtension();
    public abstract void setEncryption();
    public abstract Document buildDoc();
    
    public void displayInfo() {
        System.out.println("Document Type: " + this.getClass().getSimpleName());
        System.out.println("Extension: " + extension);
        System.out.println("Encryption: " + encryption);
    }
}


class NormalDoc extends Document {
    @Override
    public void setExtension() {
        this.extension = ".txt";
    }

    @Override
    public void setEncryption() {
        this.encryption = "None";
    }

    @Override
    public Document buildDoc() {
        setExtension();
        setEncryption();
        return this;
    }
}


class ConfidentialDoc extends Document {
    private SecretKey key;
    
    public ConfidentialDoc() {
        try {
            this.key = generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void setExtension() {
        this.extension = ".zip";
    }

    @Override
    public void setEncryption() {
        this.encryption = "AES-256";
    }

    @Override
    public Document buildDoc() {
        setExtension();
        setEncryption();
        return this;
    }
    
    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }
    
    public String encryptData(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}


class Client {
    public static void main(String[] args) {
     
        Document normalDoc = new NormalDoc().buildDoc();
        normalDoc.displayInfo();
        
        System.out.println("--------------------------------");
        
      
        ConfidentialDoc confidentialDoc = new ConfidentialDoc();
        confidentialDoc.buildDoc();
        confidentialDoc.displayInfo();
        
        try {
            String encryptedData = confidentialDoc.encryptData("Sensitive Information");
            System.out.println("Encrypted Data: " + encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}