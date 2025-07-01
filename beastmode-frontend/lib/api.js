const BASE_URL = 'http://localhost:8080/web/api/user/v1';

export const api = {
  async fetch(url, options = {}) {
    const headers = {
      'Content-Type': 'application/json',
      ...options.headers,
    };

    const response = await fetch(`${BASE_URL}${url}`, {
      ...options,
      headers,
    });

    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message || 'Request failed');
    }

    return response.json();
  },

  // Membership-specific functions
  async fetchMemberships() {
    return this.fetch('/GetAllMembershipPlan', { method: 'GET' });
  },

  async createMembership(data) {
    return this.fetch('/createMembership', {
      method: 'POST',
      body: JSON.stringify(data),
    });
  },

  async updateMembership(data) {
    return this.fetch('/updateMembership', {
      method: 'POST',
      body: JSON.stringify(data),
    });
  },

  async deleteMembership(membershipId) {
    return this.fetch(`/deleteMembership?MembershipId=${membershipId}`, {
      method: 'GET',
    });
  },

  async getMembershipById(membershipId) {
    return this.fetch(`/getMembershipById?MembershipId=${membershipId}`, {
      method: 'GET',
    });
  },
};