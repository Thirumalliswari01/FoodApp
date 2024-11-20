<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success - FoodApp</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f6f9fc 0%, #edf2f7 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
            overflow-x: hidden;
        }

        .success-card {
            background: white;
            padding: 40px;
            border-radius: 24px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 440px;
            width: 100%;
            position: relative;
            animation: cardFloat 3s ease-in-out infinite;
        }

        @keyframes cardFloat {
            0%, 100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-10px);
            }
        }

        .confetti {
            position: absolute;
            width: 10px;
            height: 10px;
            background: #ff6b6b;
            top: -20px;
        }

        @keyframes confettiFall {
            0% {
                transform: translateY(0) rotate(0deg);
                opacity: 1;
            }
            100% {
                transform: translateY(100vh) rotate(360deg);
                opacity: 0;
            }
        }

        .success-icon {
            width: 100px;
            height: 100px;
            background: #4CAF50;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 30px;
            position: relative;
            animation: pulseGlow 2s infinite;
        }

        @keyframes pulseGlow {
            0% {
                box-shadow: 0 0 0 0 rgba(76, 175, 80, 0.4);
            }
            70% {
                box-shadow: 0 0 0 20px rgba(76, 175, 80, 0);
            }
            100% {
                box-shadow: 0 0 0 0 rgba(76, 175, 80, 0);
            }
        }

        .success-icon::before {
            content: '';
            width: 40px;
            height: 20px;
            border: 5px solid white;
            border-top: none;
            border-right: none;
            transform: rotate(-45deg);
            position: absolute;
            top: 35px;
            animation: checkmarkDraw 0.6s ease-out backwards;
        }

        @keyframes checkmarkDraw {
            0% {
                width: 0;
                height: 0;
                opacity: 0;
            }
            50% {
                width: 40px;
                height: 0;
            }
            100% {
                height: 20px;
                opacity: 1;
            }
        }

        .title {
            font-size: 32px;
            color: #2d3748;
            margin-bottom: 20px;
            font-weight: 700;
            opacity: 0;
            animation: slideUp 0.6s ease-out 0.3s forwards;
        }

        @keyframes slideUp {
            from {
                transform: translateY(20px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .delivery-scene {
            height: 120px;
            position: relative;
            margin: 30px 0;
            overflow: hidden;
        }

        .delivery-bike {
            position: absolute;
            font-size: 50px;
            animation: bikeMove 3s infinite linear;
            top: 50%;
            transform: translateY(-50%);
        }

        @keyframes bikeMove {
            0% {
                left: -50px;
                transform: translateY(-50%) scale(1);
            }
            50% {
                transform: translateY(-50%) scale(1.1);
            }
            100% {
                left: 100%;
                transform: translateY(-50%) scale(1);
            }
        }

        .road {
            position: absolute;
            bottom: 20px;
            width: 100%;
            height: 4px;
            background: #e2e8f0;
            overflow: hidden;
        }

        .road::after {
            content: '';
            position: absolute;
            top: 50%;
            left: 0;
            width: 100%;
            height: 2px;
            background: repeating-linear-gradient(90deg, #cbd5e0 0px, #cbd5e0 20px, transparent 20px, transparent 40px);
            animation: roadMove 1s linear infinite;
        }

        @keyframes roadMove {
            from {
                transform: translateX(0);
            }
            to {
                transform: translateX(-40px);
            }
        }

        .order-info {
            background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
            border-radius: 16px;
            padding: 20px;
            margin: 20px 0;
            transform: scale(0.9);
            opacity: 0;
            animation: scaleIn 0.5s ease-out 0.6s forwards;
        }

        @keyframes scaleIn {
            to {
                transform: scale(1);
                opacity: 1;
            }
        }

        .order-id {
            font-size: 18px;
            color: #2d3748;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .estimated-time {
            font-size: 16px;
            color: #4a5568;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        .time-icon {
            animation: clockTick 1s infinite linear;
        }

        @keyframes clockTick {
            to {
                transform: rotate(360deg);
            }
        }

        .button {
            background: linear-gradient(135deg, #4CAF50 0%, #43a047 100%);
            color: white;
            border: none;
            padding: 16px 36px;
            border-radius: 12px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
            position: relative;
            overflow: hidden;
        }

        .button::after {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: linear-gradient(45deg, transparent, rgba(255,255,255,0.1), transparent);
            transform: rotate(45deg);
            animation: buttonGlow 2s linear infinite;
        }

        @keyframes buttonGlow {
            0% {
                transform: rotate(45deg) translateX(-100%);
            }
            100% {
                transform: rotate(45deg) translateX(100%);
            }
        }

        .button:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 20px rgba(76, 175, 80, 0.2);
        }

        .progress-bar {
            width: 100%;
            height: 6px;
            background: #e2e8f0;
            border-radius: 3px;
            margin: 20px 0;
            overflow: hidden;
        }

        .progress {
            height: 100%;
            background: linear-gradient(90deg, #4CAF50, #81c784);
            width: 0;
            animation: progressFill 2s ease-out forwards;
        }

        @keyframes progressFill {
            to {
                width: 100%;
            }
        }

        @media (max-width: 480px) {
            .success-card {
                padding: 30px 20px;
            }
            
            .title {
                font-size: 24px;
            }
            
            .delivery-scene {
                height: 80px;
            }
        }
    </style>
</head>
<body>
    <div class="success-card">
        <div class="success-icon"></div>
        <h1 class="title">Order Confirmed! </h1>
        
        <div class="delivery-scene">
    <div class="delivery-bike">&#x1F6F5;</div> <!-- Motorbike emoji -->
    <div class="road"></div>
</div>

        
        <div class="progress-bar">
            <div class="progress"></div>
        </div>
        
        <div class="order-info">
            <div class="order-id" id="orderId">Order #2024001</div>
            <div class="estimated-time">
                <span class="time-icon">⏰</span>
                <span>Estimated delivery Time: 30-45 minutes</span>
            </div>
        </div>
        
        <p class="subtitle">Your delicious food is being prepared with extra care and love!</p>
       <a href="home.jsp" class="button">Explore More Delicious Food  &#x1F924  &#x1F60B;</a>


    </div>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            // Generate and animate order ID
            const orderNumber = 'FD' + Math.floor(Math.random() * 1000000);
            const orderIdElement = document.getElementById('orderId');
            orderIdElement.style.opacity = '0';
            
            setTimeout(() => {
                orderIdElement.style.transition = 'opacity 0.5s ease';
                orderIdElement.textContent = orderNumber;
                orderIdElement.style.opacity = '1';
            }, 500);

            // Create confetti
            function createConfetti() {
                const colors = ['#ff6b6b', '#4CAF50', '#ffd93d', '#6c5ce7'];
                for (let i = 0; i < 50; i++) {
                    const confetti = document.createElement('div');
                    confetti.className = 'confetti';
                    confetti.style.left = Math.random() * 100 + '%';
                    confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
                    confetti.style.animation = confettiFall ${Math.random() * 2 + 1}s linear forwards;
                    document.body.appendChild(confetti);
                    
                    // Remove confetti after animation
                    setTimeout(() => {
                        confetti.remove();
                    }, 3000);
                }
            }

            // Initial confetti burst
            createConfetti();
            
            // Repeat confetti every few seconds
            setInterval(createConfetti, 4000);
        });
    </script>
</body>
</html>