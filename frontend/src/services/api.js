import axios from 'axios';

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_GATEWAY ?? 'http://localhost:8080',
  timeout: 10000,
});

export const accountApi = {
  createAccount(payload) {
    return apiClient.post('/account-service/accounts', payload);
  },
  fetchAccount(phoneNumber) {
    return apiClient.get(`/account-service/accounts/${encodeURIComponent(phoneNumber)}`);
  },
};

export const billingApi = {
  fetchInvoices(phoneNumber) {
    return apiClient.get('/billing-service/invoices', { params: { phoneNumber } });
  },
  payInvoice(invoiceId) {
    return apiClient.post(`/billing-service/invoices/${invoiceId}/pay`);
  },
};

export const supportApi = {
  submitRepair(payload) {
    return apiClient.post('/support-service/repairs', payload);
  },
  submitComplaint(payload) {
    return apiClient.post('/support-service/complaints', payload);
  },
  fetchTickets(phoneNumber) {
    return apiClient.get('/support-service/tickets', { params: { phoneNumber } });
  },
};

export default apiClient;
