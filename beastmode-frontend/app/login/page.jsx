
'use client';
import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { api } from '../../lib/api';
import { auth } from '../../utils/auth';
import Button from '../../components/Button';

export default function LoginPage() {
  const router = useRouter();
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await api.fetch('/login', {
        method: 'POST',
        body: JSON.stringify(formData),
      });
      
      auth.storeToken(data.userId);
      router.push('/dashboard');
    } catch (err) {
      setError(err.message || 'Login failed');
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-md mx-auto pt-20 px-4">
        <div className="bg-white p-8 rounded-lg shadow">
          <h1 className="text-2xl font-bold mb-6 text-center">Member Login</h1>
          
          {error && (
            <div className="mb-4 p-3 bg-red-100 text-red-700 rounded">
              {error}
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-sm font-medium mb-1">Email</label>
              <input
                type="email"
                required
                className="w-full px-3 py-2 border rounded-md"
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              />
            </div>

            <div>
              <label className="block text-sm font-medium mb-1">Password</label>
              <input
                type="password"
                required
                className="w-full px-3 py-2 border rounded-md"
                value={formData.password}
                onChange={(e) => setFormData({ ...formData, password: e.target.value })}
              />
            </div>

            <Button type="submit" className="w-full">
              Sign In
            </Button>
          </form>
        </div>
      </div>
    </div>
  );
}