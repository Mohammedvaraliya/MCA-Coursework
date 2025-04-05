</div> <!-- Closing container div from header.jsp -->

<style>
    #sticky-footer {
        text-align: center;
        padding: 15px;
        width: 100%;
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        margin: 0;
        z-index: 1000;
        box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
    }

    #sticky-footer p {
        margin: 0;
        font-size: 0.9rem;
        color: rgba(255, 255, 255, 0.7);
    }

    body {
        padding-bottom: 60px !important;
    }

    #sticky-footer .container {
        padding-left: 0;
        padding-right: 0;
    }
</style>

<footer id="sticky-footer" class="bg-dark text-white py-3">
    <div class="container">
        <p class="mb-0">&copy; 2025 Employee Management System. All rights reserved.</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>