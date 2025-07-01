'use client';
import { useEffect, useState } from 'react';
import { api } from '../../../lib/api';

export default function PaymentsTab() {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const data = await api.fetch('/GetAllPayments', { method: 'GET' });
        setPayments(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch payments');
      } finally {
        setLoading(false);
      }
    };

    fetchPayments();
  }, []);

  if (loading) {
    return <div className="text-center p-8">Loading payments...</div>;
  }

  if (error) {
    return <div className="text-center p-8 text-red-500">{error}</div>;
  }

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white rounded-lg shadow">
        <thead>
          <tr>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Amount
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Date
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Status
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Method
            </th>
          </tr>
        </thead>
        <tbody>
          {payments.map((payment) => (
            <tr key={payment.paymentId} className="hover:bg-gray-50">
              <td className="px-6 py-4 border-b border-gray-200">${payment.amount}</td>
              <td className="px-6 py-4 border-b border-gray-200">
                {new Date(payment.paymentDate).toLocaleString()}
              </td>
              <td className="px-6 py-4 border-b border-gray-200">{payment.paymentStatus}</td>
              <td className="px-6 py-4 border-b border-gray-200">{payment.paymentMethod}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}