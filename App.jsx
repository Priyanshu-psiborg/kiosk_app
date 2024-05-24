import {Button, NativeModules, Text, View} from 'react-native';
import {useState} from 'react';

const {KioskMode} = NativeModules;

const App = () => {
  const [isKioskMode, setIsKioskMode] = useState(false);

  const toggleKioskMode = () => {
    if (isKioskMode) {
      KioskMode.stopLockTask();
    } else {
      KioskMode.startLockTask();
    }
    setIsKioskMode(!isKioskMode);
  };
  return (
    <View>
      <Text>Test Kiosk</Text>
      <Button
        title={isKioskMode ? 'Stop Kiosk Mode' : 'Start Kiosk Mode'}
        onPress={toggleKioskMode}
      />
    </View>
  );
};

export default App;
