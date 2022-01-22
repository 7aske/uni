int money, time;
float rate, SI;

Console.Write("Enter Amount :");
money = Convert.ToInt32(Console.ReadLine());
if (money <= 0)
	throw new Exception("Money cannot be less than zero");

Console.Write("Enter Rate :");
rate = Convert.ToSingle(Console.ReadLine());
if (rate <= 0)
	throw new Exception("Rate cannot be less than zero");

Console.Write("Enter Time :");
time = Convert.ToInt32(Console.ReadLine());
if (time <= 0)
	throw new Exception("Time cannot be less than zero");

SI = money * rate * time / 100;
Console.WriteLine("Interest is :{0}", SI);
