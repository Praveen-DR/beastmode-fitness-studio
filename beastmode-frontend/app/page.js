
export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-center bg-gray-100">
      <div className="text-center">
        <h1 className="text-4xl font-bold text-gray-800">Welcome to Beast Mode Fitness Studio</h1>
        <p className="mt-4 text-lg text-gray-600">Transform your body, elevate your fitness.</p>

        <div className="mt-6 flex space-x-4">
          <a href="/register" className="px-6 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition">
            Get Started
          </a>
          <a href="/login" className="px-6 py-2 border border-indigo-600 text-indigo-600 rounded-md hover:bg-indigo-600 hover:text-white transition">
            Sign In
          </a>
        </div>
      </div>
    </main>
  );
}
