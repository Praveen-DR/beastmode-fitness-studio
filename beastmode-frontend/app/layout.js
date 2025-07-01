
import Navbar from '../components/Navbar'; // Updated import path
import '../styles/globals.css';

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body className="bg-gray-50">
        <Navbar />
        <main className="min-h-screen">{children}</main>
      </body>
    </html>
  );
}