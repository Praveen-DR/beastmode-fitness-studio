
export const auth = {
  getToken() {
    if (typeof window === 'undefined') return null;
    return localStorage.getItem('jwt');
  },

  storeToken(token) {
    localStorage.setItem('jwt', token);
  },

  clearToken() {
    localStorage.removeItem('jwt');
  },

  getPayload() {
    return this.getToken();
    // if (!token) return null;
    // return JSON.parse(atob(token.split('.')[1]));
  },

  isAuthenticated() {
    return !!this.getPayload();
  },

  getUserRole() {
    return this.getPayload()?.role || null;
  },
};