'use client';
import { useEffect, useState } from 'react';
import { api } from '../../lib/api';
import { auth } from '../../utils/auth';
import Button from '../../components/Button';

export default function PaymentsPage() {
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const userId = auth.getPayload().sub; // Get user ID from JWT payload
        const data = await api.fetch(`/payments?userId=${userId}`);
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
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">Payments</h1>
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
                <td className="px-6 py-4 border-b border-gray-200">
                  ${payment.amount}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {new Date(payment.paymentDate).toLocaleString()}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {payment.paymentStatus}
                </td>
                <td className="px-6 py-4 border-b border-gray-200">
                  {payment.paymentMethod}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}