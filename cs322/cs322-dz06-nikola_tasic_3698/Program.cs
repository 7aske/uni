using System;
using System.IO;

namespace cs322_dz06_nikola_tasic_3698 {
	internal class Program {
		public static void Main(string[] args) {
			// Kreiramo i testiramo dve razlicite implementacije logger interfejsa
			Logger consoleLogger = LoggerFactory.GetConsoleLogger(typeof(Program));
			Logger fileLogger = LoggerFactory.GetFileLogger(typeof(Program));

			// console logger pise u konzolu
			consoleLogger.Log("Testing {0}", "Console Logger");
			// dok file logger pise u fajl koji se zove isto kao prosledjena klasa
			fileLogger.Log("Testing {0}", "File Logger");
		}
	}

	interface Logger {
		void Log(string format, params object[] arg);
	}

	abstract class AbstractLogger : Logger {
		protected string Name;

		protected AbstractLogger(string name) {
			Name = name;
		}

		public abstract void Log(string format, params object[] arg);
	}

	class ConsoleLogger : AbstractLogger {
		public ConsoleLogger(string name) : base(name) {
		}

		public override void Log(string format, params object[] arg) {
			Console.WriteLine();
			Console.WriteLine(format, arg);
		}
	}

	class FileLogger : AbstractLogger {
		private readonly StreamWriter _writer;

		public FileLogger(string name) : base(name) {
			_writer = new StreamWriter(Name + ".log");
		}

		public override void Log(string format, params object[] arg) {
			_writer.WriteLine(format, arg);
		}
	}

	class LoggerFactory {
		public static Logger GetConsoleLogger(Type type) {
			if (type == null) throw new ArgumentNullException(nameof(type));
			return new ConsoleLogger(nameof(type));
		}

		public static Logger GetFileLogger(Type type) {
			if (type == null) throw new ArgumentNullException(nameof(type));
			return new FileLogger(nameof(type));
		}
	}
}