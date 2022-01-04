namespace cs322_dz11_nikola_tasic_3698.Logger;

// Za zadatak broj 2
public class LoggerExample {
	// Interface
	public interface Logger {
		void Log(string format, params object[] arg);
	}

	// Apstraktna klasa
	public abstract class AbstractLogger : Logger {
		protected string Name;

		protected AbstractLogger(string name) {
			Name = name;
		}

		public abstract void Log(string format, params object[] arg);
	}

	// Konkretna implementacija 1
	public class ConsoleLogger : AbstractLogger {
		public ConsoleLogger(string name) : base(name) {
		}

		public override void Log(string format, params object[] arg) {
			Console.WriteLine();
			Console.WriteLine(format, arg);
		}
	}

	// Konkretna implementacija 1
	public class FileLogger : AbstractLogger {
		private readonly StreamWriter _writer;

		public FileLogger(string name) : base(name) {
			_writer = new StreamWriter(Name + ".log");
		}

		public override void Log(string format, params object[] arg) {
			_writer.WriteLine(format, arg);
		}
	}

	public class LoggerFactory {
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