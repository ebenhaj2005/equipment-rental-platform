console.log('app.js loaded');

const API_BASE = 'http://localhost:8080/api';
let currentUser = null;

// Check if user is logged in
async function checkAuthStatus() {
    try {
        const response = await fetch(`${API_BASE}/auth/me`, {
            credentials: 'include'
        });

        if (response.ok) {
            currentUser = await response.json();
            document.getElementById('loginLink').style.display = 'none';
            document.getElementById('logoutBtn').style.display = 'block';
        } else {
            currentUser = null;
            document.getElementById('loginLink').style.display = 'block';
            document.getElementById('logoutBtn').style.display = 'none';
        }
    } catch (error) {
        console.error('Auth check failed:', error);
        currentUser = null;
    }
}

// Logout
async function logout() {
    try {
        await fetch(`${API_BASE}/auth/logout`, {
            method: 'POST',
            credentials: 'include'
        });

        currentUser = null;
        alert('Uitgelogd');
        window.location.href = 'index.html';
    } catch (error) {
        console.error('Logout failed:', error);
    }
}

// Update Cart Count
async function updateCartCount() {
    if (!currentUser) {
        document.getElementById('cartCount').textContent = '0';
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/cart`, { credentials: 'include' });
        if (response.ok) {
            const cart = await response.json();
            document.getElementById('cartCount').textContent = cart.items.length;
        }
    } catch (error) {
        console.error('Failed to update cart count:', error);
    }
}

// Show Error
function showError(elementId, message) {
    const errorDiv = document.getElementById(elementId);
    if (errorDiv) {
        errorDiv.textContent = message;
        errorDiv.style.display = 'block';

        setTimeout(() => {
            errorDiv.style.display = 'none';
        }, 5000);
    }
}