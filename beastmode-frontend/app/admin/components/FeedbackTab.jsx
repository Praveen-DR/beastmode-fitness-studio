'use client';
import { useEffect, useState } from 'react';
import { api } from '../../../lib/api';

export default function FeedbackTab() {
  const [feedbackList, setFeedbackList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchFeedback = async () => {
      try {
        const data = await api.fetch('/GetAllFeedback', { method: 'GET' });
        setFeedbackList(data);
      } catch (err) {
        setError(err.message || 'Failed to fetch feedback');
      } finally {
        setLoading(false);
      }
    };

    fetchFeedback();
  }, []);

  if (loading) {
    return <div className="text-center p-8">Loading feedback...</div>;
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
              Type
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Rating
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Content
            </th>
            <th className="px-6 py-3 border-b-2 border-gray-200 text-left text-sm font-semibold text-gray-600">
              Date
            </th>
          </tr>
        </thead>
        <tbody>
          {feedbackList.map((feedback) => (
            <tr key={feedback.feedbackId} className="hover:bg-gray-50">
              <td className="px-6 py-4 border-b border-gray-200">{feedback.feedbackType}</td>
              <td className="px-6 py-4 border-b border-gray-200">{feedback.rating}/5</td>
              <td className="px-6 py-4 border-b border-gray-200">{feedback.content}</td>
              <td className="px-6 py-4 border-b border-gray-200">
                {new Date(feedback.createdAt).toLocaleString()}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}