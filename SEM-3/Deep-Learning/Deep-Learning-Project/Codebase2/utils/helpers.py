import json
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.metrics import classification_report, confusion_matrix
import pickle
import os

def load_json_data(file_path, n_lines=None):
    """
    Load JSON data from file
    """
    data = []
    with open(file_path, 'r', encoding='utf-8') as f:
        for i, line in enumerate(f):
            if n_lines and i >= n_lines:
                break
            try:
                data.append(json.loads(line))
            except json.JSONDecodeError:
                continue
    return data

def save_processed_data(data, filename):
    """
    Save processed data to pickle file
    """
    os.makedirs('../data/processed', exist_ok=True)
    with open(f'../data/processed/{filename}', 'wb') as f:
        pickle.dump(data, f)

def load_processed_data(filename):
    """
    Load processed data from pickle file
    """
    with open(f'../data/processed/{filename}', 'rb') as f:
        return pickle.load(f)

def plot_class_distribution(labels, title="Class Distribution"):
    """
    Plot class distribution
    """
    plt.figure(figsize=(12, 6))
    label_series = pd.Series(labels)
    counts = label_series.value_counts()
    
    plt.subplot(1, 2, 1)
    counts.plot(kind='bar')
    plt.title(title)
    plt.xticks(rotation=45)
    
    plt.subplot(1, 2, 2)
    counts.plot(kind='pie', autopct='%1.1f%%')
    plt.title('Percentage Distribution')
    
    plt.tight_layout()
    plt.show()
    
    return counts

def evaluate_model(model, X_test, y_test, model_name=""):
    """
    Comprehensive model evaluation
    """
    from sklearn.metrics import accuracy_score, f1_score, classification_report, confusion_matrix
    
    y_pred = model.predict(X_test)
    
    # Convert probabilities to class labels if needed
    if len(y_pred.shape) > 1 and y_pred.shape[1] > 1:
        y_pred = np.argmax(y_pred, axis=1)
        if len(y_test.shape) > 1 and y_test.shape[1] > 1:
            y_test = np.argmax(y_test, axis=1)
    
    accuracy = accuracy_score(y_test, y_pred)
    f1 = f1_score(y_test, y_pred, average='weighted')
    
    print(f"=== {model_name} Evaluation ===")
    print(f"Accuracy: {accuracy:.4f}")
    print(f"F1-Score: {f1:.4f}")
    print("\nClassification Report:")
    print(classification_report(y_test, y_pred))
    
    # Plot confusion matrix
    plt.figure(figsize=(10, 8))
    cm = confusion_matrix(y_test, y_pred)
    sns.heatmap(cm, annot=True, fmt='d', cmap='Blues')
    plt.title(f'Confusion Matrix - {model_name}')
    plt.ylabel('True Label')
    plt.xlabel('Predicted Label')
    plt.show()
    
    return accuracy, f1, y_pred