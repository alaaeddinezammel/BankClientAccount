package com.sg.bankclientaccount.business.feature;

import static org.assertj.core.api.Fail.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.sg.bankclientaccount.business.port.output.HistoryPrinterPortOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HistoryFeatureTest {

  private HistoryFeature historyFeature;

  private HistoryPrinterPortOutput historyPrinterPortOutput;

  @BeforeEach
  void setUp() {
    this.historyPrinterPortOutput = Mockito.mock(HistoryPrinterPortOutput.class);

    this.historyFeature = new HistoryFeature(historyPrinterPortOutput);
  }

  @Test
  void shouldPrintEmptyHistory() {
    // When
    doNothing().when(historyPrinterPortOutput).print(isA(String.class));

    historyFeature.findOperations();

    // Then
    verify(historyPrinterPortOutput).print("OPERATION | DATE | AMOUNT | BALANCE");
  }

  @Test
  void shouldRenderHistory() {
    fail("Not yet implemented");
  }


}
