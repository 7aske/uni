#pragma once

#include "Teacher.h"

class Department
{
public:
	Department(Teacher *pcTeacher = NULL)
			: m_pcTeacher(pcTeacher) { }

	Teacher *m_pcTeacher;
};