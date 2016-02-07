package Lexico;

import java.io.FileReader;

public class CharReader {

    private boolean mReady = false;
    private FileReader mFileReader = null;
    private Character mPeekChar;

    public CharReader() {

    }

    public void Cerrar() {

        try {
            mFileReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

    }

    public boolean listo() {
        return mReady == true;
    }

    public char ReadNextChar() {

        if (!mReady) {
            return Character.MIN_VALUE;
        }

        char result = Character.MIN_VALUE;
        try {
            if (mPeekChar == null) {
                int temp = mFileReader.read();
                if (temp == -1) {
                    mReady = false;
                    return Character.MIN_VALUE;
                }
                result = (char)temp;
            } else {

                result = mPeekChar;
            }
            mPeekChar = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return result;
    }

    public char peekNextChar() {

        if (!mReady) {
            return Character.MIN_VALUE;
        }
        try {
            if (mPeekChar == null) {
                int temp = mFileReader.read();
                if (temp == -1) {
                    mReady = false;
                    return Character.MIN_VALUE;
                }
                mPeekChar = (char)temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return mPeekChar;
    }

    public void inicializar(String pFilePath) {
        try {
            mFileReader = new FileReader(pFilePath);
            mReady = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            mReady = false;
        }
        finally {
        }
    }
}