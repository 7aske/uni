#ifndef TEACHER_H
#define TEACHER_H

class Teacher
{
private:
	std::string m_strName;
public:
	Teacher(std::string strName)
			: m_strName(strName) {}

	std::string GetName() { return m_strName; }
};

#endif