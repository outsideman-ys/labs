#include <iostream>
#include <deque>
#include <map>
#include <time.h>


//void swap(std::deque<int>::iterator first, std::deque<int>::iterator second) {
//    int temp = *first;
//    *first = *second;
//    *second = temp;
//}
//
//void sort(std::deque<int>::iterator begin, std::deque<int>::iterator end) {
//    std::deque<int>::iterator iterator;
//    int k = 0;
//    for (std::deque<int>::iterator i = begin; i != end-1; i++, k++) {
//        iterator = end - 1 - k;
//        for (std::deque<int>::iterator j = begin; j != iterator; j++) {
//            if (*j > *(j + 1)) {
//                swap(j, (j + 1));
//            }
//        }
//    }
//}

int main()
{
    std::deque<int> deq;
    long time1, time2;
    srand(time(0));

    std::cout << "DEQUE" << std::endl;
    std::cout << "PUSH BACK" << std::endl;
    time1 = clock();
    for (int i = 0; i < 2000000; i++) {
        deq.push_back(rand());
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "ERASE ITER" << std::endl;
    std::deque<int>::iterator iter2 = deq.begin();
    time1 = clock();
    for (int i = 0; i < 2000000; i++) {
        deq.erase(iter2);
        iter2 = deq.begin();
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "PUSH FRONT" << std::endl;
    time1 = clock();
    for (int i = 0; i < 2000000; i++) {
        deq.push_front(rand());
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "POP FRONT" << std::endl;
    time1 = clock();
    while (!deq.empty()) {
        deq.pop_front();
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "INSERT ITER" << std::endl;
    std::deque<int>::iterator iter = deq.begin();
    time1 = clock();
    for (int i = 0; i < 2000000; i++) {
        deq.emplace(iter, rand());
        iter = deq.begin();
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "POP BACK" << std::endl;
    time1 = clock();
    while (!deq.empty()) {
        deq.pop_back();
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    //std::cout << "SEARCH" << std::endl;
    //auto iterSize = deq.begin() + (rand() % deq.size());
    //int number2 = *iterSize;
    //time1 = clock();
    //for (auto iter3 = deq.begin(); iter3 != deq.end(); iter3++) {
    //    if (number2 == *iter3) {
    //        break;
    //    }
    //    else continue;
    //}
    //time2 = clock();
    //std::cout << time2 - time1 << " ticks" << std::endl;
    //std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    //std::cout << "SORT" << std::endl;
    //time1 = clock();
    //sort(deq.begin(), deq.end());
    //time2 = clock();
    //std::cout << time2 - time1 << " ticks" << std::endl;
    //std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;
    //for (auto iter = deq.begin(); iter != deq.end(); iter++) {
    //    std::cout << *iter << " ";
    //}

    std::cout << std::endl;
    std::cout << "MULTIMAP" << std::endl;
    std::multimap<int, int> map;

    std::cout << "INSERT" << std::endl;
    time1 = clock();
    for (int i = 0; i < 2000000; i++) {
        map.insert(std::make_pair(rand(), rand()));
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "FIND" << std::endl;
    std::multimap<int, int>::iterator iter5 = map.begin();
    time1 = clock();
    //for (int i = 0; i < 2000000; i++) {
    //    int key = (*iter5).first;
    //    map.find(key);
    //    iter5 = std::next(map.begin(), i);
    //}
    int key = (*iter5).first;
    map.find(key);
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    std::cout << "ERASE" << std::endl;
    std::multimap<int, int>::iterator iter4 = std::next(map.begin(), 4);
    time1 = clock();
    while (!map.empty()) {
        map.erase(iter4);
        iter4 = map.begin();
    }
    time2 = clock();
    std::cout << time2 - time1 << " ticks" << std::endl;
    std::cout << (double)(time2 - time1) / CLOCKS_PER_SEC << " sec" << std::endl;

    

    //for (const auto& e : map) {
    //    std::cout << e.first << " " << e.second << std::endl;
    //}


    return 0;
}

