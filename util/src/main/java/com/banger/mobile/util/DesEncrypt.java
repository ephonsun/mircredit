package com.banger.mobile.util;

//import java.security.Key;
//import java.security.SecureRandom;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

public class DesEncrypt {
//	Key key ;
//	 
//    public DesEncrypt() {
// 
//    }
// 
//    public DesEncrypt(String str) {
//       setKey(str); // 生成密匙
//    }
// 
//    public Key getKey() {
//       return key ;
//    }
// 
//    public void setKey(Key key) {
//       this . key = key;
//    }
// 
//    /**
//      * 根据参数生成 KEY
//      */
//    public void setKey(String strKey) {
//       try {
//           KeyGenerator _generator = KeyGenerator.getInstance ( "DES" );
//           _generator.init( new SecureRandom(strKey.getBytes()));
//           this . key = _generator.generateKey();
//           _generator = null ;
//       } catch (Exception e) {
//           throw new RuntimeException(
//                  "Error initializing SqlMap class. Cause: " + e);
//       }
//    }
// 
//    /**
//      * 加密 String 明文输入 ,String 密文输出
//      */
//    public String encryptStr(String strMing) {
//       byte [] byteMi = null ;
//       byte [] byteMing = null ;
//       String strMi = "" ;
//       BASE64Encoder base64en = new BASE64Encoder();
//       try {
//           byteMing = strMing.getBytes( "UTF8" );
//           byteMi = this .encryptByte(byteMing);
//           strMi = base64en.encode(byteMi);
//       } catch (Exception e) {
//           throw new RuntimeException(
//                  "Error initializing SqlMap class. Cause: " + e);
//       } finally {
//           base64en = null ;
//           byteMing = null ;
//           byteMi = null ;
//       }
//       return strMi;
//    }
// 
//    /**
//      * 解密 以 String 密文输入 ,String 明文输出
//      *
//      * @param strMi
//      * @return
//      */
//    public String decryptStr(String strMi) {
//       BASE64Decoder base64De = new BASE64Decoder();
//       byte [] byteMing = null ;
//       byte [] byteMi = null ;
//       String strMing = "" ;
//       try {
//           byteMi = base64De.decodeBuffer(strMi);
//           byteMing = this .decryptByte(byteMi);
//           strMing = new String(byteMing, "UTF8" );
//       } catch (Exception e) {
//           throw new RuntimeException(
//                  "Error initializing SqlMap class. Cause: " + e);
//       } finally {
//           base64De = null ;
//           byteMing = null ;
//           byteMi = null ;
//       }
//       return strMing;
//    }
// 
//    /**
//      * 加密以 byte[] 明文输入 ,byte[] 密文输出
//      *
//      * @param byteS
//      * @return
//      */
//    private byte [] encryptByte( byte [] byteS) {
//       byte [] byteFina = null ;
//       Cipher cipher;
//       try {
//           cipher = Cipher.getInstance ( "DES" );
//           cipher.init(Cipher. ENCRYPT_MODE , key );
//           byteFina = cipher.doFinal(byteS);
//       } catch (Exception e) {
//           throw new RuntimeException(
//                  "Error initializing SqlMap class. Cause: " + e);
//       } finally {
//           cipher = null ;
//       }
//       return byteFina;
//    }
// 
//    /**
//      * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
//      *
//      * @param byteD
//      * @return
//      */
//    private byte [] decryptByte( byte [] byteD) {
//       Cipher cipher;
//       byte [] byteFina = null ;
//       try {
//           cipher = Cipher.getInstance ( "DES" );
//           cipher.init(Cipher. DECRYPT_MODE , key );
//           byteFina = cipher.doFinal(byteD);
//       } catch (Exception e) {
//           throw new RuntimeException(
//                  "Error initializing SqlMap class. Cause: " + e);
//       } finally {
//           cipher = null ;
//       }
//       return byteFina;
//    }

 
}
