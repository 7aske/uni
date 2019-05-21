#include <string>
#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <set>

using namespace std;

int main(int argc, char* argv[]) {

	// zadatak 5
	string str("some r*ndom st*ing *ith **terisks");
	auto it = str.begin();

	while(it != str.end()){
		if (*it == '*'){
			*it = '+';
		}
		it++;
	}
	cout << str << endl;

	// zadatak 2
	istream_iterator<int> iterator(cin);
	vector<int> vector;

	for (int i = 0; i < 9; ++i) {
		vector.push_back(*iterator);
		iterator++;
	}
	std::sort(vector.begin(), vector.end());
	for (int j = 0; j < vector.size(); ++j) {
		cout << vector[j] << endl;
	}

	// zadatak 6 :)
	char buf[256];
	sprintf(buf, "cat %s | sort | uniq -c", argv[1]);
	cout << buf << endl;
	system(buf);
	return 0;
}