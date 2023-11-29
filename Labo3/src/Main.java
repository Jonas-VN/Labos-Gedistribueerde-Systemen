import javax.crypto.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, SignatureException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {
/*
        // Voorbereiding
        Person person1 = new Person("John", "London", "123456789");
        Person person1Fake = new Person("John", "Londen", "123456789");
        Person person2 = new Person("Jonas", "Gent", "04789542162");

        byte[] person1Bytes = person1.getBytes();
        byte[] person1FakeBytes = person1Fake.getBytes();
        byte[] person2Bytes = person2.getBytes();

        // Hashing / Message Digest
        System.out.println("Hashing / Message Digest");
        String person1Hash = getSHA256Hash(person1Bytes);
        String person1FakeHash = getSHA256Hash(person1FakeBytes);
        String person2Hash = getSHA256Hash(person2Bytes);

        System.out.println("SHA-256 hash van person1: " + person1Hash);
        System.out.println("SHA-256 hash van person1Fake: " + person1FakeHash);
        System.out.println("SHA-256 hash van person2: " + person2Hash);

        boolean hashesMatch = person1Hash.equals(person1FakeHash);
        System.out.println("Zijn de hashes gelijk? " + hashesMatch);
        // Zelf met een kleine aanpassing zijn de hashes compleet verschillend.

        // Symmetrische Encryptie
        // AES: 128, 192, 256 bits
        // TripleDes: 168 bits
        System.out.println("\n\nSymmetrische Encryptie");
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();

        Cipher aesCipher = Cipher.getInstance("AES");

        // Encrypt
        String text = "Hello, world!";
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteCipherText = aesCipher.doFinal(text.getBytes());

        // Decrypt
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, aesCipher.getParameters());
        byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
        String decryptedText = new String(bytePlainText);

        System.out.println("Original text: " + text);
        System.out.println("Decrypted text: " + decryptedText);

        // Asymmetrische Encryptie
        System.out.println("\n\nAsymmetrische Encryptie");
        KeyPairGenerator keyGen2 = KeyPairGenerator.getInstance("RSA");
        keyGen2.initialize(2048);
        KeyPair keyPair = keyGen2.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        Cipher rsaCipher = Cipher.getInstance("RSA");

        // Encrypt
        String text2 = "Hello, world!";
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] byteCipherText2 = rsaCipher.doFinal(text2.getBytes());

        // Decrypt
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytePlainText2 = rsaCipher.doFinal(byteCipherText2);
        String decryptedText2 = new String(bytePlainText2);

        System.out.println("Original text: " + text2);
        System.out.println("Decrypted text: " + decryptedText2);

        // Digitale handtekening
        System.out.println("\n\nDigitale handtekening");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Gebruik een geschikte sleutellengte
        KeyPair keyPair2 = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey2 = keyPair2.getPrivate();
        PublicKey publicKey2 = keyPair2.getPublic();

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey2);
        signature.update(person2Bytes);
        byte[] digitalSignature = signature.sign();

        String signatureString = Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("Digitale handtekening: " + signatureString);

        signature.initVerify(publicKey2);
        signature.update(person2Bytes);
        boolean verified = signature.verify(digitalSignature);
        if (verified) {
            System.out.println("De digitale handtekening is geldig.");
        } else {
            System.out.println("De digitale handtekening is niet geldig.");
        }

        // Deel 2
        // 2.2 Opdracht
        // Aanspreken van keystore in Java

        // Load Werner's keystore (store1.jks)
        KeyStore wernerKeyStore = KeyStore.getInstance("JKS");
        String wernerFileName = "C:\\Users\\jonas\\Downloads\\portecle-1.11\\portecle-1.11\\store1";
        FileInputStream wernerFis = new FileInputStream(wernerFileName);
        char[] wernerPassword = "store1".toCharArray(); // replace with actual password
        wernerKeyStore.load(wernerFis, wernerPassword);
        wernerFis.close();

        // Get Freya's certificate from Werner's keystore
        X509Certificate freyacert = (X509Certificate) wernerKeyStore.getCertificate("freya");

        // Encrypt a message with Freya's public key
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, freyacert.getPublicKey());
        String message = "Hello, world!";
        byte[] byteCipherText = rsaCipher.doFinal(message.getBytes());

        // Load Freya's keystore (store2.jks)
        KeyStore freyaKeyStore = KeyStore.getInstance("JKS");
        String freyaFileName = "C:\\Users\\jonas\\Downloads\\portecle-1.11\\portecle-1.11\\store2";
        FileInputStream freyaFis = new FileInputStream(freyaFileName);
        char[] freyaPassword = "".toCharArray(); // replace with actual password
        freyaKeyStore.load(freyaFis, freyaPassword);
        freyaFis.close();

        // Get Freya's private key from her keystore
        PrivateKey freyaPrivateKey = (PrivateKey) freyaKeyStore.getKey("freya", freyaPassword);

        // Decrypt the cipher text with Freya's private key
        rsaCipher.init(Cipher.DECRYPT_MODE, freyaPrivateKey);
        byte[] bytePlainText = rsaCipher.doFinal(byteCipherText);
        String decryptedMessage = new String(bytePlainText);

        // Print the original and decrypted messages
        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(byteCipherText));
        System.out.println("Decrypted message: " + decryptedMessage);
*/
        String message = "Secure communication";
        byte[] messageHash = getSHA256Hash(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Message Hash: " + new String(messageHash));
        KeyPairGenerator keyPairGeneratorA = KeyPairGenerator.getInstance("RSA");
        keyPairGeneratorA.initialize(2048);
        KeyPair keyPairA = keyPairGeneratorA.generateKeyPair();

        PrivateKey privateKeyA = keyPairA.getPrivate();
        PublicKey publicKeyA = keyPairA.getPublic();

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKeyA);
        signature.update(messageHash);
        byte[] digitalSignature = signature.sign();
        String fullText = new String(digitalSignature) + "||||" + message;
        byte[] fullTextBytes = fullText.getBytes(StandardCharsets.UTF_8);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom());
        SecretKey secretKey = keyGen.generateKey();

        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] part1 = aesCipher.doFinal(fullTextBytes);
        System.out.println("Part 1: " + new String(part1));

        KeyPairGenerator keyPairGeneratorB = KeyPairGenerator.getInstance("RSA");
        keyPairGeneratorB.initialize(2048);
        KeyPair keyPairB = keyPairGeneratorB.generateKeyPair();

        PrivateKey privateKeyB = keyPairB.getPrivate();
        PublicKey publicKeyB = keyPairB.getPublic();

        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKeyB);
        byte[] part2 = rsaCipher.doFinal(secretKey.getEncoded());
        System.out.println("Part 2: " + new String(part2));

        // Decrypt Part 2 (Party B)
        Cipher rsaCipherDecrypt = Cipher.getInstance("RSA");
        rsaCipherDecrypt.init(Cipher.DECRYPT_MODE, privateKeyB);
        byte[] decryptedSecretKeyBytes = rsaCipherDecrypt.doFinal(part2);

        SecretKey decryptedSecretKey = new SecretKeySpec(decryptedSecretKeyBytes, 0, decryptedSecretKeyBytes.length, "AES");

        // Decrypt Part 1 (Party B)
        Cipher aesCipherDecrypt = Cipher.getInstance("AES");
        aesCipherDecrypt.init(Cipher.DECRYPT_MODE, decryptedSecretKey);
        byte[] decryptedPart1 = aesCipherDecrypt.doFinal(part1);
        String fullString = new String(decryptedPart1);
        String[] parts = fullString.split("\\|\\|\\|\\|");
        System.out.println("Decrypted Part 1: " + parts[0]);
        System.out.println("Decrypted Part 2: " + parts[1]);

        // Verify the Digital Signature (Party B)
        Signature signatureVerify = Signature.getInstance("SHA256withRSA");
        signatureVerify.initVerify(publicKeyA);
        signatureVerify.update(messageHash);

        boolean signatureValid = signatureVerify.verify(parts[0].getBytes(StandardCharsets.UTF_8));

        if (signatureValid) {
            System.out.println("Digital Signature Verified: The message is authentic.");
            String decryptedMessage = new String(messageHash, StandardCharsets.UTF_8);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } else {
            System.out.println("Digital Signature Invalid: The message may have been tampered with.");
        }



    }

    public static byte[] getSHA256Hash(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}