#include <iostream>
#include <cstring>

using namespace std;

class Strings
{
public:
    
    Strings(int currSize, const char* charArray) {
        if (currSize > maxSize) {
            maxSize = ChangeSize(currSize);
            this->charArray = new char[maxSize];
        }
        else {
            this->charArray = new char[maxSize];
        }
        for (int i = 0; i < currSize; i++)
            this->charArray[i] = charArray[i];
        this->currSize = currSize;
    }
    
    void PrintStr() {
        for (int i = 0; i < currSize; i++)
            cout << charArray[i];
        cout << endl;
    }

    int ChangeSize(int currSize) {
        int pow = 2;
        while (currSize > maxSize * pow) {
            pow *= 2;
        }
        return maxSize * pow;
            
    }

    void ChangeStr(int currSize, const char* charArray) {
        if (currSize > maxSize) {
            maxSize = ChangeSize(currSize);
            delete[] this->charArray;
            this->charArray = new char[maxSize];
        }
        for (int i = 0; i < currSize; i++)
            this->charArray[i] = charArray[i];
        this->currSize = currSize;
    }

    int FindStr(const char* subCharArray) {
        char* pointer = strstr(charArray, subCharArray);
        if (pointer == NULL) {
            return -1;
        }
        else {
            return pointer - charArray;
        }
    }

    ~Strings() {
        delete[] charArray;
    }


private:
    char* charArray;
    int maxSize = 2;
    int currSize;
};


int main()
{
    const char* kal = "Hello world";
    Strings gavno(strlen(kal), kal);
    gavno.PrintStr();
    const char* kal1 = "Hello bob";
    gavno.ChangeStr(strlen(kal1), kal1);
    gavno.PrintStr();
    cout << gavno.FindStr("bob") << endl;

    return 0;
}
