import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import glob

def simple_analysis():
    files = glob.glob("times_mode*.csv")
    
    results = []
    
    for file in files:
        try:
            mode = int(file.split('mode')[1].split('_')[0])
            n = int(file.split('_n')[1].split('.')[0])
            
            data = pd.read_csv(file, header=None).values[0]
            
            results.append({
                'mode': mode,
                'n': n,
                'mean': np.mean(data),
                'min': np.min(data),
                'max': np.max(data)
            })
        except:
            continue
    
    df = pd.DataFrame(results)
    
    plt.figure(figsize=(10, 6))
    for mode in sorted(df['mode'].unique()):
        mode_data = df[df['mode'] == mode].sort_values('n')
        plt.plot(mode_data['n'], mode_data['mean'], 'o-', label=f'Tryb {mode}')
    
    plt.xlabel('Liczba filozofów')
    plt.ylabel('Średni czas oczekiwania [ms]')
    plt.title('Porównanie średnich czasów oczekiwania')
    plt.legend()
    plt.grid(True)
    plt.savefig('simple_comparison.png')
    plt.show()
    
    return df

df_simple = simple_analysis()
print(df_simple)